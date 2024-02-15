terraform {

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}

/*terraform {
  backend "s3" {
    bucket = "terraform-statefile-backend-us-east-1"
    key    = "jenkins/terraform.tfstate.devopsacad"
    region = "us-east-1"
  }
}*/