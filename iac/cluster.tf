resource "kind_cluster" "garage_cluster" {
  name = "cluster-local-dev"

  kind_config {
    kind        = "Cluster"
    api_version = "kind.x-k8s.io/v1alpha4"

    node {
      role  = "control-plane"
      image = "kindest/node:v1.28.0"

      extra_port_mappings {
        container_port = 80
        host_port      = 9080
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 443
        host_port      = 9443
        protocol       = "TCP"
      }

      extra_port_mappings {
        container_port = 30300
        host_port      = 3000
        protocol       = "TCP"
      }

      kubeadm_config_patches = [
        <<-EOF
        kind: InitConfiguration
        nodeRegistration:
          kubeletExtraArgs:
            node-labels: "ingress-ready=true"
        EOF
      ]
    }
  }
}