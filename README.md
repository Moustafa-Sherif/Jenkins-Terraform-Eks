<img src="https://github.com/profebass99/jenkins-terraform-eks/assets/104143346/3f52921a-da83-46fd-8f6e-628de6695421" width="5800">


# Project
A simple Jenkins CI/CD application deployment pipeline to Kubernetes on the AWS infrastructure. Automated with Terraform 

# Overview
Triggering Jenkins via push even to a Git repository to initiate a build process. Jenkins compiles the Java code into an executable WAR file. This WAR file is then packaged with Tomcat into a Docker image and subsequently pushed to Amazon Elastic Container Registry (ECR). Following this, Jenkins employs Terraform to provision an Amazon Elastic Kubernetes Service (EKS) cluster, deploying the Docker image into the cluster.
