variable "resource_name" {
  description = "The value to be used in resource naming"
  type        = string
  default     = "spring-boot-app"
}

variable "public_key_path" {
  description = "Path to a public key file for connecting EC2 instance"
  type        = string
  default     = "id_rsa.pub"
}

variable "image" {
  description = "The app image to be used. Example: <your_username>/<your_repository_name>:1.0"
  type        = string
}

variable "active_profiles" {
  description = "Active profiles to be used by the app"
  type        = string
  default     = "dev"
}

variable "architecture" {
  description = "The image architecture"
  type        = string
  default     = "x86_64"
}

variable "restricted_access" {
  description = "Restrict access to the app only from current IP"
  type        = bool
  default     = false
}
