variable "github_pat" {
  type      = string
  sensitive = true
}

variable "sonar_admin_password" {
  type      = string
  sensitive = true
}

variable "owasp_cache_host_path" {
  type        = string
  description = "OWASP cache host path for the Persistent Volume Claim (PVC)."
}

variable "NVD_API_KEY" {
  type        = string
  sensitive   = true
  description = "NVD API key for accessing the National Vulnerability Database (NVD) API."
  default     = ""
}