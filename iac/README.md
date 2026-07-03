# IaC

## Infrastructure

- Github Actions Runner
- KinD
- Postgres
- Jaeger
- Sonarqube
- Floci

## Terraform - Installation

**Linux**

```sh
wget -O - https://apt.releases.hashicorp.com/gpg | sudo gpg --dearmor -o /usr/share/keyrings/hashicorp-archive-keyring.gpg
```
```sh
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(grep -oP '(?<=UBUNTU_CODENAME=).*' /etc/os-release || lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/hashicorp.list
```
```sh
sudo apt update && sudo apt install terraform
```

**Windows**

- https://developer.hashicorp.com/terraform/install
- Extract in `C:\devtools\terraform`
- Add `C:\devtools\terraform` to Path

### Terraform - K8S

**Uninstall**

```sh
terraform init -upgrade
terraform destroy -auto-approve
```
```sh
terraform state rm kubernetes_deployment.github_runner
terraform state rm kubernetes_service.github_runner
```
```sh
sudo rm $(which kubectl)
```

**Kubernetes setup**

```sh
terraform init
```
```sh
terraform plan
```
```sh
terraform apply -auto-approve
```
```sh
terraform apply -auto-approve -replace
```
```sh
source use-kubeconfig.sh
hash -r
```

**Valide k8s**

```sh
kubectl get nodes
```
```sh
kubectl get pods
```

**Analise pods**

```sh
kubectl logs -f -l app=github-runner
```
```sh
kubectl logs -l app=api-garage --tail=100 --previous
```
