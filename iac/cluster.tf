resource "kind_cluster" "garage_cluster" {
  name            = "cluster-local-dev"
  kubeconfig_path = "${path.module}/cluster-local-dev-config"

  kind_config {
    kind        = "Cluster"
    api_version = "kind.x-k8s.io/v1alpha4"

    containerd_config_patches = [
      <<-TOML
      [plugins."io.containerd.grpc.v1.crt".registry.mirrors."kind-registry:5000"]
        endpoint = ["http://kind-registry:5000"]
      [plugins."io.containerd.grpc.v1.crt".registry.mirrors."localhost:5001"]
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