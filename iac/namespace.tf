resource "kubernetes_namespace" "garage" {
  metadata {
    name = "garage"
  }
}
