resource "kubernetes_namespace" "garage" {
  depends_on = [kind_cluster.garage_cluster]

  metadata {
    name = "garage"
  }

  # Rancher injeta annotations (cattle.io/status, lifecycle.cattle.io/...) que
  # não fazem parte desta config; sem isso o plan mostra drift a cada apply.
  lifecycle {
    ignore_changes = [metadata[0].annotations]
  }
}