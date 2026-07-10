resource "kubernetes_deployment" "loki" {
  metadata {
    name      = "loki"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  spec {
    replicas = 1
    selector { match_labels = { app = "loki" } }

    template {
      metadata { labels = { app = "loki" } }
      spec {
        container {
          name  = "loki"
          image = "grafana/loki:latest"

          port {
            container_port = 3100
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "loki" {
  metadata {
    name      = "loki"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  spec {
    selector = { app = "loki" }

    port {
      port        = 3100
      target_port = 3100
      node_port   = 30310
    }

    type = "NodePort"
  }
}
