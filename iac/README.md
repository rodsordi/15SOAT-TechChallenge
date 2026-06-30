# IaC

## Terraform

- https://developer.hashicorp.com/terraform/install

```sh
wget -O - https://apt.releases.hashicorp.com/gpg | sudo gpg --dearmor -o /usr/share/keyrings/hashicorp-archive-keyring.gpg
```

```sh
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(grep -oP '(?<=UBUNTU_CODENAME=).*' /etc/os-release || lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/hashicorp.list
```

```sh
sudo apt update && sudo apt install terraform
```

### Terraform - K8S

**Kubernetes reset**

```sh
terraform init -upgrade
terraform destroy -auto-approve
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
kubectl get nodes
```
