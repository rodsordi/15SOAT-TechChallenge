resource "kubernetes_service_account" "github_runner_sa" {
  depends_on = [kind_cluster.garage_cluster]

  metadata {
    name      = "github-runner-sa"
    namespace = kubernetes_namespace.garage.metadata[0].name
  }
}

# 2. Associação da ServiceAccount ao papel de cluster-admin (permissão total)
resource "kubernetes_cluster_role_binding" "github_runner_admin" {
  depends_on = [kubernetes_service_account.github_runner_sa]

  metadata {
    name = "github-runner-admin-binding"
  }

  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "cluster-admin"
  }

  subject {
    kind      = "ServiceAccount"
    name      = kubernetes_service_account.github_runner_sa.metadata[0].name
    namespace = kubernetes_service_account.github_runner_sa.metadata[0].namespace
  }
}
