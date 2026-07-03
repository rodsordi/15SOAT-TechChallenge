resource "null_resource" "build_and_load_image" {
  depends_on = [
    kind_cluster.garage_cluster
  ]

  triggers = {
    dockerfile_hash = filesha256("${path.module}/Dockerfile-runner")
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
    replicas = 2
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
            name = "RUNNER_NAME"
            value_from {
              field_ref {
                field_path = "metadata.name"
              }
            }
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
          env {
            name  = "RUN_AS_ROOT"
            value = "true"
          }
          env {
            name  = "SONAR_TOKEN"
            value = sensitive(trimspace(data.local_file.sonar_token.content))
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
