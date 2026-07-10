provider "kind" {}

resource "kind_cluster" "garage_cluster" {
  name = "cluster-local-dev"

  kind_config {
    kind        = "Cluster"
    api_version = "kind.x-k8s.io/v1alpha4"

    node {
      role = "control-plane"

      extra_port_mappings {
        container_port = 30080
        host_port      = 8080
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30432
        host_port      = 5432
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30666
        host_port      = 4566
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 31686
        host_port      = 16686
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 31717
        host_port      = 4317
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 31718
        host_port      = 4318
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30900
        host_port      = 9000
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30909
        host_port      = 9090
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30310
        host_port      = 3100
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30300
        host_port      = 3000
        protocol       = "TCP"
      }
    }
  }
}

provider "kubernetes" {
  host                   = kind_cluster.garage_cluster.endpoint
  client_certificate     = kind_cluster.garage_cluster.client_certificate
  client_key             = kind_cluster.garage_cluster.client_key
  cluster_ca_certificate = kind_cluster.garage_cluster.cluster_ca_certificate
}

output "kubeconfig_path" {
  description = "Caminho do kubeconfig gerado pelo provider kind. Use: source iac/use-kubeconfig.sh"
  value       = kind_cluster.garage_cluster.kubeconfig_path
}
