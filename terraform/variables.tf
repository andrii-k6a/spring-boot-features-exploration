variable "resource_name" {
  description = "The value to use for resource naming"
  type        = string
  default     = "spring-boot-app"
}

variable "image" {
  description = "The app image to use for running the app. Template: <your_username>/<your_repository_name>:1.0"
  type        = string
}

variable "architecture" {
  description = "The image architecture"
  type        = string
  default     = "x86_64"
}

variable "instance_type" {
  description = "Instance type to use for the EC2 instance"
  type        = string
  default     = "t2.micro"
}

variable "active_profiles" {
  description = "Active profiles to be used by the app"
  type        = string
  default     = "dev"
}

variable "restricted_access" {
  description = "Make the app accessible from current IP only. By default it's publicly available."
  type        = bool
  default     = false
}

variable "public_key_path" {
  description = "Path to a public key file for connecting to EC2 instance"
  type        = string
  default     = "id_rsa.pub"
}
