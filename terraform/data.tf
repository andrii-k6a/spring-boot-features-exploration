data "http" "current_ip" {
  count = var.restricted_access ? 1 : 0
  url   = "http://ipv4.icanhazip.com"
}

data "aws_ami" "amazon_linux" {
  owners      = ["amazon"]
  most_recent = true

  filter {
    name   = "architecture"
    values = [var.architecture]
  }

  filter {
    name   = "name"
    values = ["amzn2-ami-kernel-5.10-hvm-2.0.20221004.0-*"]
  }

  filter {
    name   = "block-device-mapping.volume-type"
    values = ["gp2"]
  }
}
