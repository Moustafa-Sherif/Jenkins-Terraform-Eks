def fetchCode() {
    echo "Pull Source code from GitHub"
    git branch: 'main', url: 'https://github.com/Moustafa-Sherif/Jenkins-Terraform-Eks.git'
}

def buildCode() {
    echo "Build App with Maven"
    sh 'mvn clean install -DskipTests'
}

def buildImage() {
    echo "Build app with docker"
    dockerImage = docker.build(awsEcrRegistry + ":$BUILD_NUMBER", "./Docker-files/app/multistage/")
}

def pushImage() {
    echo "Logging in to AWS ECR"
    sh 'aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 447360774718.dkr.ecr.us-east-2.amazonaws.com'
    echo "Push Docker Image to ECR"
    def dockerImage = docker.build("jenkins-pipeline:$BUILD_NUMBER", "./Docker-files/app/multistage/")
    docker.withRegistry(imageRegUrl, 'ecr:us-east-2:AWS_Credentials') {
        dockerImage.push("$BUILD_NUMBER")
        dockerImage.push('latest')
    }
}

def provisionEksCluster() {
    withAWS(credentials: 'JenkinsAWSCLI', region: 'us-east-2') {
        dir('terraform') {
            sh 'terraform init -reconfigure'
            sh 'terraform destroy --auto-approve'
            EKS_CLUSTER_NAME = sh(
                script: "terraform output clusterName",
                returnStdout: true
            ).trim()
        }
    }
}

def connectEks() {
    echo "${EKS_CLUSTER_NAME}"
    withAWS(credentials: 'JenkinsAWSCLI', region: 'us-east-2') {
        sh "aws eks update-kubeconfig --name ${EKS_CLUSTER_NAME} --region ${awsRegion}"
        sh 'kubectl get nodes'
        sh 'kubectl apply -f app.yaml'
        sh 'sudo cp /var/lib/jenkins/.kube/config ~/.kube/.'
        sh 'sudo chown -R ubuntu:ubuntu config'
    }
}

return this 

