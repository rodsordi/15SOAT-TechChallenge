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

      # Port-forward com auto-recuperação
      kubectl -n default port-forward svc/sonarqube 19000:9000 >/dev/null 2>&1 &
      trap 'kill $! 2>/dev/null || true' EXIT

      # Aguarda SonarQube ficar UP (com timeouts para resiliência)
      for i in {1..100}; do
        curl -s -m 5 --connect-timeout 2 http://127.0.0.1:19000/api/system/status | grep -q '"status":"UP"' && break
        kill -0 $! 2>/dev/null || kubectl -n default port-forward svc/sonarqube 19000:9000 >/dev/null 2>&1 &
        sleep 3
      done

      # Altera senha e revoga token anterior (ignora erros e evita travar)
      curl -s -m 10 --connect-timeout 3 -u admin:admin -X POST "http://127.0.0.1:19000/api/users/change_password?login=admin&previousPassword=admin&password=${var.sonar_admin_password}" >/dev/null || true
      curl -s -m 10 --connect-timeout 3 -u "admin:${var.sonar_admin_password}" -X POST "http://127.0.0.1:19000/api/user_tokens/revoke?name=terraform-token" >/dev/null || true

      # Gera o novo token e finaliza o port-forward para evitar travamentos do shell/pipes
      TOKEN=$(curl -s -m 10 --connect-timeout 3 -u "admin:${var.sonar_admin_password}" -X POST "http://127.0.0.1:19000/api/user_tokens/generate?name=terraform-token" | grep -o '"token":"[^"]*' | cut -d'"' -f4 || true)
      kill $! 2>/dev/null || true  # Força o encerramento do kubectl port-forward em background
      [ -n "$TOKEN" ] || { echo "Falha ao obter token do SonarQube" >&2; exit 1; }
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
