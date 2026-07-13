resource "kind_cluster" "garage_cluster" {
  name            = "cluster-local-dev"
  kubeconfig_path = "${path.module}/cluster-local-dev-config"

  kind_config {
    kind        = "Cluster"
    api_version = "kind.x-k8s.io/v1alpha4"

    containerd_config_patches = [
      <<-TOML
      [plugins."io.containerd.grpc.v1.cri".registry.mirrors."kind-registry:5000"]
        endpoint = ["http://kind-registry:5000"]
      [plugins."io.containerd.grpc.v1.cri".registry.mirrors."localhost:5001"]
        endpoint = ["http://kind-registry:5000"]
      TOML
    ]

    node {
      role = "control-plane"
    }
  }

  depends_on = [
    docker_container.kind_registry
  ]
}

# Remove um container "kind-registry" órfão (de uma execução anterior sem tracking
# no state) ANTES do Terraform tentar criar o novo, evitando conflito de nome.
resource "null_resource" "kind_registry_cleanup" {
  provisioner "local-exec" {
    command = "docker rm -f kind-registry 2>/dev/null || true"
  }
}

resource "docker_container" "kind_registry" {
  depends_on = [null_resource.kind_registry_cleanup]

  name     = "kind-registry"
  image    = "registry:2"
  start    = true
  must_run = true

  ports {
    internal = 5000
    external = 5001
  }

  networks_advanced {
    name = "kind"
  }
}

# Configuração do ConfigMap para o Kubelet descobrir o Registro (Padrão do Kind)
resource "kubernetes_config_map" "local_registry_hosting" {
  metadata {
    name      = "local-registry-hosting"
    namespace = "kube-public"
  }

  data = {
    "localRegistryHosting.v1" = <<-EOF
      host: "localhost:5001"
      help: "https://kind.sigs.k8s.io/docs/user/local-registry/"
    EOF
  }

  depends_on = [kind_cluster.garage_cluster]
}