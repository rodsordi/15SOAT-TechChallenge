resource "kubernetes_config_map" "prometheus_config" {
  metadata {
    name      = "prometheus-config"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  data = {
    "prometheus.yml" = file("${path.module}/../prometheus/prometheus.yml")
  }
}

resource "kubernetes_deployment" "prometheus" {
  metadata {
    name      = "prometheus"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  spec {
    replicas = 1
    selector { match_labels = { app = "prometheus" } }

    template {
      metadata { labels = { app = "prometheus" } }
      spec {
        container {
          name  = "prometheus"
          image = "prom/prometheus:latest"

          args = [
            "--config.file=/etc/prometheus/prometheus.yml",
            "--web.enable-otlp-receiver",
          ]

          port {
            container_port = 9090
          }

          volume_mount {
            name       = "prometheus-config"
            mount_path = "/etc/prometheus/prometheus.yml"
            sub_path   = "prometheus.yml"
          }
        }

        volume {
          name = "prometheus-config"
          config_map {
            name = kubernetes_config_map.prometheus_config.metadata[0].name
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "prometheus" {
  metadata {
    name      = "prometheus"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  spec {
    selector = { app = "prometheus" }

    port {
      port        = 9090
      target_port = 9090
      node_port   = 30909
    }

    type = "NodePort"
  }
}
