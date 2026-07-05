resource "kubernetes_deployment" "sonarqube" {
  metadata { name = "sonarqube" }

  spec {
    replicas = 1
    selector { match_labels = { app = "sonarqube" } }

    template {
      metadata { labels = { app = "sonarqube" } }
      spec {
        container {
          name  = "sonarqube"
          image = "sonarqube:community"

          port {
            container_port = 9000
          }

          env {
            name  = "SONAR_ES_BOOTSTRAP_CHECKS_DISABLE"
            value = "true"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "sonarqube" {
  metadata { name = "sonarqube" }

  spec {
    selector = { app = "sonarqube" }
    port { port = 9000 }
    type = "ClusterIP"
  }
}

resource "null_resource" "sonar_setup" {
  depends_on = [kubernetes_deployment.sonarqube, kubernetes_service.sonarqube]

  triggers = {
    password_hash = sha256(var.sonar_admin_password)
  }

  provisioner "local-exec" {
    interpreter = ["bash", "-c"]

    command = <<-EOT
      set -eo pipefail
      export MSYS_NO_PATHCONV=1
      export KUBECONFIG="${replace(kind_cluster.garage_cluster.kubeconfig_path, "\\", "/")}"

      kubectl -n default port-forward svc/sonarqube 19000:9000 >/dev/null 2>&1 &
      PF_PID=$!
      trap 'kill $PF_PID 2>/dev/null || true' EXIT

      echo "Aguardando SonarQube responder..."
      for i in {1..60}; do
        STATUS=$(curl -s http://127.0.0.1:19000/api/system/status | grep -o '"status":"[^"]*' | cut -d'"' -f4 || true)
        [ "$STATUS" = "UP" ] && break
        sleep 3
      done

      curl -sf -u admin:admin -X POST "http://127.0.0.1:19000/api/users/change_password?login=admin&previousPassword=admin&password=${var.sonar_admin_password}" || true

      # revoga um token com o mesmo nome antes de gerar, garantindo idempotencia
      curl -sf -u "admin:${var.sonar_admin_password}" -X POST "http://127.0.0.1:19000/api/user_tokens/revoke?name=terraform-token" || true

      TOKEN=$(curl -sf -u "admin:${var.sonar_admin_password}" -X POST "http://127.0.0.1:19000/api/user_tokens/generate?name=terraform-token" | grep -o '"token":"[^"]*' | cut -d'"' -f4)

      if [ -z "$TOKEN" ]; then
        echo "ERRO: falha ao gerar o token do SonarQube" >&2
        exit 1
      fi

      echo -n "$TOKEN" > "${path.module}/.sonar_token"
    EOT
  }
}

data "local_file" "sonar_token" {
  depends_on = [null_resource.sonar_setup]
  filename   = "${path.module}/.sonar_token"
}

output "sonar_token" {
  description = "Token gerado via API do SonarQube (usar em SONAR_TOKEN / mvn -Dsonar.token)."
  value       = trimspace(data.local_file.sonar_token.content)
  sensitive   = true
}
