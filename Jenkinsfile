pipeline {
    agent any

    environment {
        PROJECT = "DevHub-backend"
        DOCKERHUB_ID = "goonerd"
        DATE = sh(script: "date +%Y%m%d", returnStdout: true).trim()
        BUILD_TAG = "${PROJECT}-${DATE}-${BUILD_NUMBER}"
        INFRA_REPO = "https://github.com/Goonerd17/DevHub-infra.git"
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'dev', url: 'https://github.com/Goonerd17/DevHub-backend.git'
            }
        }

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh """
                    echo "$PASS" | docker login -u "$USER" --password-stdin
                    docker build -t $DOCKERHUB_ID/$PROJECT:$BUILD_TAG .
                    docker push $DOCKERHUB_ID/$PROJECT:$BUILD_TAG
                    docker logout
                    """
                }
            }
        }

        stage('Update Infra Repo') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                    sh """
                    git config --global user.name "Jenkins"
                    git config --global user.email "jenkins@devhub.local"

                    git clone https://$GIT_USER:$GIT_TOKEN@github.com/Goonerd17/DevHub-infra.git
                    cd DevHub-infra/infra/k8s/devhub-backend

                    # 이미지 태그 변경
                    sed -i 's#image: goonerd/DevHub-backend:.*#image: goonerd/DevHub-backend:'"$BUILD_TAG"'#' deployment.yml

                    git add .
                    git commit -m "[CI] Update backend image to $BUILD_TAG"
                    git push origin main
                    """
                }
            }
        }
    }
}
