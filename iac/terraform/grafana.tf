resource "kubernetes_config_map" "grafana_provisioning" {
  metadata {
    name      = "grafana-provisioning"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  data = {
    "datasources-jaeger.yml"     = file("${path.module}/../grafana/provisioning/datasources/jaeger.yml")
    "datasources-loki.yml"       = file("${path.module}/../grafana/provisioning/datasources/loki.yml")
    "datasources-prometheus.yml" = file("${path.module}/../grafana/provisioning/datasources/prometheus.yml")
    "dashboards-provider.yml"    = file("${path.module}/../grafana/provisioning/dashboards/dashboards.yml")
  }
}

resource "kubernetes_config_map" "grafana_dashboards_json" {
  metadata {
    name      = "grafana-dashboards-json"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  data = {
    "jaeger-traces.json"      = file("${path.module}/../grafana/provisioning/dashboards/json/jaeger-traces.json")
    "loki-metrics.json"       = file("${path.module}/../grafana/provisioning/dashboards/json/loki-metrics.json")
    "prometheus-metrics.json" = file("${path.module}/../grafana/provisioning/dashboards/json/prometheus-metrics.json")
  }
}

resource "kubernetes_deployment" "grafana" {
  metadata {
    name      = "grafana"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  spec {
    replicas = 1
    selector { match_labels = { app = "grafana" } }

    template {
      metadata { labels = { app = "grafana" } }
      spec {
        container {
          name  = "grafana"
          image = "grafana/grafana:latest"

          port {
            container_port = 3000
          }

          env {
            name  = "GF_AUTH_ANONYMOUS_ENABLED"
            value = "true"
          }
          env {
            name  = "GF_AUTH_ANONYMOUS_ORG_ROLE"
            value = "Admin"
          }

          volume_mount {
            name       = "grafana-provisioning"
            mount_path = "/etc/grafana/provisioning/datasources/jaeger.yml"
            sub_path   = "datasources-jaeger.yml"
          }
          volume_mount {
            name       = "grafana-provisioning"
            mount_path = "/etc/grafana/provisioning/datasources/loki.yml"
            sub_path   = "datasources-loki.yml"
          }
          volume_mount {
            name       = "grafana-provisioning"
            mount_path = "/etc/grafana/provisioning/datasources/prometheus.yml"
            sub_path   = "datasources-prometheus.yml"
          }
          volume_mount {
            name       = "grafana-provisioning"
            mount_path = "/etc/grafana/provisioning/dashboards/dashboards.yml"
            sub_path   = "dashboards-provider.yml"
          }
          volume_mount {
            name       = "grafana-dashboards-json"
            mount_path = "/etc/grafana/provisioning/dashboards/json/jaeger-traces.json"
            sub_path   = "jaeger-traces.json"
          }
          volume_mount {
            name       = "grafana-dashboards-json"
            mount_path = "/etc/grafana/provisioning/dashboards/json/loki-metrics.json"
            sub_path   = "loki-metrics.json"
          }
          volume_mount {
            name       = "grafana-dashboards-json"
            mount_path = "/etc/grafana/provisioning/dashboards/json/prometheus-metrics.json"
            sub_path   = "prometheus-metrics.json"
          }
        }

        volume {
          name = "grafana-provisioning"
          config_map {
            name = kubernetes_config_map.grafana_provisioning.metadata[0].name
          }
        }
        volume {
          name = "grafana-dashboards-json"
          config_map {
            name = kubernetes_config_map.grafana_dashboards_json.metadata[0].name
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "grafana" {
  metadata {
    name      = "grafana"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }

  spec {
    selector = { app = "grafana" }

    port {
      port        = 3000
      target_port = 3000
      node_port   = 30300
    }

    type = "NodePort"
  }
}
