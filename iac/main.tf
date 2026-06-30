terraform {
  required_providers {
    kind = { source = "tehcyx/kind", version = "~> 0.8.0" }
    kubernetes = { source = "hashicorp/kubernetes", version = "~> 2.23" }
  }
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

          # Variáveis corrigidas (sem ponto e vírgula e em linhas separadas)
          env {
            name  = "POSTGRES_USER"
            value = "postgres"
          }
          env {
            name  = "POSTGRES_PASSWORD"
            value = "senha_secreta"
          }
          env {
            name  = "POSTGRES_DB"
            value = "meu_banco_local"
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