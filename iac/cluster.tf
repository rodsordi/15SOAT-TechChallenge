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

output "kubeconfig_path" {
  description = "Caminho do kubeconfig gerado pelo provider kind. Use: source iac/use-kubeconfig.sh"
  value       = kind_cluster.garage_cluster.kubeconfig_path
}
