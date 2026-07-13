resource "kubernetes_namespace" "garage" {
  depends_on = [kind_cluster.garage_cluster]

  metadata {
    name = "garage"
  }
}