terraform {
  required_providers {
    kind = {
      source  = "tehcyx/kind"
      version = "~> 0.8.0"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.23"
    }
    local = {
      source  = "hashicorp/local"
      version = "~> 2.4"
    }
    null = {
      source  = "hashicorp/null"
      version = "~> 3.2"
    }
  }
}

variable "github_pat" {
  type      = string
  sensitive = true
}

provider "kind" {}

resource "kind_cluster" "garage_cluster" {
  name = "cluster-local-dev"
}

provider "kubernetes" {
  host                   = kind_cluster.garage_cluster.endpoint
  client_certificate     = kind_cluster.garage_cluster.client_certificate
  client_key             = kind_cluster.garage_cluster.client_key
  cluster_ca_certificate = kind_cluster.garage_cluster.cluster_ca_certificate
}

resource "kubernetes_service_account" "github_runner_sa" {
  depends_on = [kind_cluster.garage_cluster]

  metadata {
    name      = "github-runner-sa"
    namespace = "default"
  }
}

# 2. Associação da ServiceAccount ao papel de cluster-admin (permissão total)
resource "kubernetes_cluster_role_binding" "github_runner_admin" {
  depends_on = [kubernetes_service_account.github_runner_sa]

  metadata {
    name = "github-runner-admin-binding"
  }

  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "cluster-admin"
  }

  subject {
    kind      = "ServiceAccount"
    name      = kubernetes_service_account.github_runner_sa.metadata[0].name
    namespace = kubernetes_service_account.github_runner_sa.metadata[0].namespace
  }
}

resource "kubernetes_deployment" "postgres" {
  metadata { name = "postgres" }

  spec {
    replicas = 1
    selector { match_labels = { app = "postgres" } }

    template {
      metadata { labels = { app = "postgres" } }
      spec {
        container {
          name  = "postgres"
          image = "postgres:15"

          port {
            container_port = 5432
          }

          env {
            name  = "POSTGRES_USER"
            value = "postgres"
          }
          env {
            name  = "POSTGRES_PASSWORD"
            value = "postgres"
          }
          env {
            name  = "POSTGRES_DB"
            value = "postgres"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "postgres" {
  metadata { name = "postgres" }

  spec {
    selector = { app = "postgres" }
    port { port = 5432 }
    type = "ClusterIP"
  }
}

resource "null_resource" "build_and_load_image" {
  depends_on = [
    kind_cluster.garage_cluster
  ]

  triggers = {
    always_run = timestamp()
  }

  provisioner "local-exec" {
    interpreter = ["bash", "-c"]

    command = <<-EOT
      set -e
      export MSYS_NO_PATHCONV=1
      docker build --network host -t custom-runner:latest -f "${path.module}/Dockerfile-runner" "${path.module}"
      docker save -o custom-runner.tar custom-runner:latest
      docker cp custom-runner.tar cluster-local-dev-control-plane:/custom-runner.tar
      docker exec cluster-local-dev-control-plane ctr -n k8s.io images import /custom-runner.tar
      rm -f custom-runner.tar
    EOT
  }
}

resource "kubernetes_deployment" "github_runner" {
  depends_on = [
    null_resource.build_and_load_image,
    kubernetes_cluster_role_binding.github_runner_admin
  ]

  metadata {
    name = "github-runner"
  }

  spec {
    replicas = 1
    selector { match_labels = { app = "github-runner" } }

    template {
      metadata { labels = { app = "github-runner" } }
      spec {
        service_account_name = kubernetes_service_account.github_runner_sa.metadata[0].name

        container {
          name              = "github-runner"
          image             = "custom-runner:latest"
          image_pull_policy = "Never"

          env {
            name  = "REPO_URL"
            value = "https://github.com/rodsordi/15SOAT-TechChallenge"
          }
          env {
            name  = "RUNNER_NAME"
            value = "runner-k8s-local"
          }
          env {
            name  = "ACCESS_TOKEN"
            value = var.github_pat
          }
          env {
            name  = "LABELS"
            value = "local,k8s,kind"
          }
          env {
            name  = "DOCKER_HOST"
            value = "tcp://localhost:2375"
          }

          volume_mount {
            name       = "containerd-sock"
            mount_path = "/run/containerd/containerd.sock"
          }

          security_context {
            privileged = true
          }
        }

        container {
          name  = "dind"
          image = "docker:27-dind"
          args  = ["--host=tcp://0.0.0.0:2375", "--host=unix:///var/run/docker.sock"]

          env {
            name  = "DOCKER_TLS_CERTDIR"
            value = ""
          }

          port {
            container_port = 2375
          }

          security_context {
            privileged = true
          }
        }

        volume {
          name = "containerd-sock"

          host_path {
            path = "/run/containerd/containerd.sock"
            type = "Socket"
          }
        }
      }
    }
  }
}
