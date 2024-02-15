variable "ingressports" {
  type    = list(number)
  default = [8080, 22]
}
variable "instance_type" {
  type = string
  default = "t2.medium"
}

variable "environment" {
  type = string 
  default = "Terraform-Dev"
}

variable "region" {
  type = string
  default = "us-east-1"
}
variable "key_name" {
  type = string 
  default = "Terraform"
}
