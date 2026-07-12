# Cert-Manager
resource "kubernetes_namespace" "cert_manager" {
  metadata {
    name = "cert-manager"
  }
}

resource "helm_release" "cert_manager" {
  name       = "cert-manager"
  repository = "https://charts.jetstack.io"
  chart      = "cert-manager"
  namespace  = kubernetes_namespace.cert_manager.metadata[0].name
  version    = "v1.13.3"

  set {
    name  = "installCRDs"
    value = "true"
  }
}

# Rancher
resource "kubernetes_namespace" "cattle_system" {
  metadata {
    name = "cattle-system"
  }
}

resource "helm_release" "rancher" {
  depends_on = [helm_release.cert_manager]

  name       = "rancher"
  repository = "https://releases.rancher.com/server-charts/stable"
  chart      = "rancher"
  namespace  = kubernetes_namespace.cattle_system.metadata[0].name
  version    = "v2.8.5"

  set {
    name  = "hostname"
    value = "rancher.localhost"
  }

  set {
    name  = "bootstrapPassword"
    value = "admin123456"
  }

  set {
    name  = "replicas"
    value = "1"
  }

  set {
    name  = "ingress.tls.source"
    value = "rancher"
  }
}

resource "kubernetes_service" "rancher_nodeport" {
  depends_on = [helm_release.rancher]

  metadata {
    name      = "rancher-nodeport"
    namespace = kubernetes_namespace.cattle_system.metadata[0].name
  }

  spec {
    selector = {
      "app" = "rancher"
    }

    port {
      name        = "http"
      port        = 80
      target_port = 80
      node_port   = 30080
    }

    type = "NodePort"
  }
}