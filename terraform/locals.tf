locals {
  ec2_sg_ingress_cidr_block = var.restricted_access ? "${chomp(data.http.current_ip[0].response_body)}/32" : "0.0.0.0/0"
}