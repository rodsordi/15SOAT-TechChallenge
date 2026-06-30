terraform {
  required_providers {
    kind = {
      source  = "tehcyx/kind"
      version = "~> 0.8.0"
    }
    helm = {
      source  = "hashicorp/helm"
      version = "~> 2.12"
    }
  }
}

provider "kind" {}

resource "kind_cluster" "meu_cluster" {
  name = "cluster-local-dev"

  kind_config {
    kind        = "Cluster"
    api_version = "kind.x-k8s.io/v1alpha4"

    node {
      role = "control-plane"
    }
  }
}

provider "helm" {
  kubernetes {
    host                   = kind_cluster.meu_cluster.endpoint
    client_certificate     = kind_cluster.meu_cluster.client_certificate
    client_key             = kind_cluster.meu_cluster.client_key
    cluster_ca_certificate = kind_cluster.meu_cluster.cluster_ca_certificate
  }
}

resource "helm_release" "postgres" {
  name       = "meu-postgres"

  repository = "oci://registry-1.docker.io/bitnamicharts"
  chart      = "postgresql"
  namespace  = "default"

  version    = "15.5.0"

  wait = true
  timeout = 600

  set {
    name  = "auth.postgresPassword"
    value = "senha_super_secreta"
  }

  set {
    name  = "auth.database"
    value = "meu_banco_dev"
  }

  set {
    name  = "primary.persistence.enabled"
    value = "false"
  }
}

output "postgres_host" {
  value       = "${helm_release.postgres.name}-postgresql.default.svc.cluster.local"
  description = "Endereço interno do banco de dados dentro do Kubernetes"
}