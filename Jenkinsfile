pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = ''
        DOCKER_CREDENTIALS = ''
        KUBECONFIG_CREDENTIALS = ''
        NAMESPACE = ''
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/tu-repo/microservices-repo.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['discovery-server', 'api-gateway', 'config-server']
                    for (service in services) {
                        sh """
                        docker build -t ${DOCKER_REGISTRY}/${service}:latest ./${service}
                        """
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS) {
                        def services = ['discovery-server', 'api-gateway', 'config-server']
                        for (service in services) {
                            sh """
                            docker push ${DOCKER_REGISTRY}/${service}:latest
                            """
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS, variable: 'KUBECONFIG')]) {
                        sh """
                        export KUBECONFIG=${KUBECONFIG}
                        kubectl apply -f kubernetes/
                        kubectl rollout status deployment/discovery-server -n ${NAMESPACE}
                        kubectl rollout status deployment/api-gateway -n ${NAMESPACE}
                        kubectl rollout status deployment/config-server -n ${NAMESPACE}
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check the logs.'
        }
    }
}
