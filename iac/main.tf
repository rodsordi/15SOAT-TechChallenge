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

resource "kubernetes_deployment" "github_runner" {
  metadata {
    name = "github-runner"
  }

  spec {
    replicas = 1
    selector { match_labels = { app = "github-runner" } }

    template {
      metadata { labels = { app = "github-runner" } }
      spec {
        container {
          name  = "github-runner"
          image = "myoung34/github-runner:latest"

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

          security_context {
            privileged = true
          }
        }
      }
    }
  }
}