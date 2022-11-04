resource "aws_instance" "app" {
  ami                         = data.aws_ami.amazon_linux.image_id
  instance_type               = var.instance_type
  security_groups             = [aws_security_group.ec2.name]
  key_name                    = aws_key_pair.ec2_ssh.id
  associate_public_ip_address = true

  root_block_device {
    delete_on_termination = true
    encrypted             = false
    volume_size           = 8
    volume_type           = "gp2"
  }

  user_data = templatefile("user_data.tftpl", { image = var.image, active_profiles = var.active_profiles })

  tags = {
    Name = var.resource_name
  }
}

resource "aws_key_pair" "ec2_ssh" {
  key_name   = var.resource_name
  public_key = file(var.public_key_path)
}

resource "aws_security_group" "ec2" {
  name = "${var.resource_name}-sg"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [local.ec2_sg_ingress_cidr_block]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = [local.ec2_sg_ingress_cidr_block]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
}
