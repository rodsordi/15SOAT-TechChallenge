resource "kubernetes_deployment" "floci" {
  metadata { name = "floci" }

  spec {
    replicas = 1
    selector { match_labels = { app = "floci" } }

    template {
      metadata { labels = { app = "floci" } }
      spec {
        enable_service_links = false

        container {
          name  = "floci"
          image = "floci/floci:latest"

          port {
            container_port = 4566
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "floci" {
  metadata { name = "floci" }

  spec {
    selector = { app = "floci" }
    port { port = 4566 }
    type = "ClusterIP"
  }
}
