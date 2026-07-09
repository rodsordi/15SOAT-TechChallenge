resource "kubernetes_deployment" "jaeger" {
  metadata { name = "jaeger" }

  spec {
    replicas = 1
    selector { match_labels = { app = "jaeger" } }

    template {
      metadata { labels = { app = "jaeger" } }
      spec {
        container {
          name  = "jaeger"
          image = "jaegertracing/all-in-one:latest"

          port {
            container_port = 16686
          }

          port {
            container_port = 4317
          }

          port {
            container_port = 4318
          }

          env {
            name  = "COLLECTOR_OTLP_ENABLED"
            value = "true"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "jaeger" {
  metadata { name = "jaeger" }

  spec {
    selector = { app = "jaeger" }

    port {
      name        = "ui"
      port        = 16686
      target_port = 16686
    }

    port {
      name        = "otlp-grpc"
      port        = 4317
      target_port = 4317
    }

    port {
      name        = "otlp-http"
      port        = 4318
      target_port = 4318
    }

    type = "ClusterIP"
  }
}
