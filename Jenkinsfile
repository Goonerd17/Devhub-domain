pipeline {
    agent any

    environment {
        PROJECT = "DevHub-backend"
        DOCKERHUB_ID = "goonerd"
        DATE = sh(script: "date +%Y%m%d", returnStdout: true).trim()
        BUILD_TAG = "${PROJECT}-${DATE}-${BUILD_NUMBER}".toLowerCase()
        IMAGE_NAME = "${DOCKERHUB_ID}/${PROJECT}".toLowerCase()
        INFRA_REPO = "https://github.com/Goonerd17/DevHub-infra.git"
    }

    stages {
        stage('Clean Workspace') {
            steps {
                echo "🧹 Cleaning workspace..."
                deleteDir()
            }
        }

        stage('Checkout Backend') {
            steps {
                echo "🔄 Checking out backend repository..."
                git branch: 'dev', url: 'https://github.com/Goonerd17/DevHub-backend.git'
            }
        }

        stage('Build Jar') {
            steps {
                echo "🏗 Building backend jar..."
                sh "chmod +x gradlew"
                sh "./gradlew clean build"
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh 'echo "$PASS" | docker login -u "$USER" --password-stdin'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo "🛠 Building Docker image..."
                sh "docker build -t $IMAGE_NAME:$BUILD_TAG ."
            }
        }

        stage('Docker Push') {
            steps {
                echo "📤 Pushing Docker image..."
                sh "docker push $IMAGE_NAME:$BUILD_TAG"
                sh "docker logout"
            }
        }

        stage('Update Infra Repo') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                    sh 'git config --global user.name "Jenkins"'
                    sh 'git config --global user.email "jenkins@devhub.local"'
                    sh "git clone https://$GIT_USER:$GIT_TOKEN@github.com/Goonerd17/DevHub-infra.git"
                    sh "cd DevHub-infra/infra/k8s/devhub-backend && sed -i 's#image: goonerd/DevHub-backend:.*#image: $IMAGE_NAME:$BUILD_TAG#' deployment.yml"
                    sh "cd DevHub-infra/infra/k8s/devhub-backend && git add . && git commit -m '[CI] Update backend image to $BUILD_TAG' || echo 'No changes to commit'"
                    sh "cd DevHub-infra/infra/k8s/devhub-backend && git push origin main"
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build and Infra update complete!"
        }
        failure {
            echo "❌ Pipeline failed!"
        }
    }
}
