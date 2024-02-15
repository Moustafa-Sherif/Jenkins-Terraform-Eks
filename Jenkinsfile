def gv

pipeline {
    agent any
    environment {
        awsEcrCreds = '447360774718'
        awsEcrRegistry = "public.ecr.aws/x1l1w6i5/jenkins-pipeline"
        imageRegUrl = "public.ecr.aws/x1l1w6i5/jenkins-pipeline"
        awsRegion = "us-east-2"
    }
    tools {
        maven "maven"
        jdk "JDk 1.8"
    }
    stages {
        stage ("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage ("Fetch Code") {
            steps {
                script {
                    gv.fetchCode()
                }
            }
        }
        stage ('Build') {
            steps {
                script {
                    gv.buildCode()
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/*.war'
                }
            }
        }
        stage ('Build Image') {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage ('Push Image to ECR') {
            steps {
                script {
                    gv.pushImage()
                }
            }
        }
        stage ('provision-eks-cluster') {
            steps {
                script {
                    gv.provisionEksCluster()
                }
            }
        }
        stage ('connect-to-eks-cluster') {
            steps {
                script {
                    gv.connectEks()
                }
            }
        }
    }
}
