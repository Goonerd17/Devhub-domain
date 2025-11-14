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
                checkout scm  // 현재 Jenkins job의 브랜치에 맞게 체크아웃
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

        stage('Docker Build & Push') {
            steps {
                echo "🛠 Building & pushing Docker image..."
                sh """
                    docker build -t $IMAGE_NAME:$BUILD_TAG .
                    docker push $IMAGE_NAME:$BUILD_TAG
                    docker logout
                """
            }
        }

        stage('Update Infra Repo') {
            steps {
                script {
                    // 현재 브랜치명 가져오기
                    def currentBranch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()
                    echo "📦 Current branch: ${currentBranch}"

                    // 브랜치별 디렉토리 매핑
                    def targetDir = ""
                    def targetBranch = ""
                    if (currentBranch == "dev") {
                        targetDir = "infra/k8s/dev/devhub-backend"
                        targetBranch = "dev"
                    } else if (currentBranch == "main") {
                        targetDir = "infra/k8s/prd/devhub-backend"
                        targetBranch = "main"
                    } else {
                        error "❌ Unsupported branch: ${currentBranch}. Only 'dev' or 'main' are allowed."
                    }

                    withCredentials([usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                        sh 'git config --global user.name "Jenkins"'
                        sh 'git config --global user.email "jenkins@devhub.local"'

                        // 인프라 레포 클론
                        sh "git clone -b ${targetBranch} https://${GIT_USER}:${GIT_TOKEN}@github.com/Goonerd17/DevHub-infra.git"

                        dir("DevHub-infra/${targetDir}") {
                            sh """
                                echo "📝 Updating deployment.yml image tag..."
                                sed -i "s#image: goonerd/devhub-backend:.*#image: ${IMAGE_NAME}:${BUILD_TAG}#" deployment.yml
                                grep 'image:' deployment.yml
                            """

                            // 변경 커밋 및 푸시
                            sh """
                                git add deployment.yml
                                git commit -m '[CI] Update backend image to ${BUILD_TAG}' --allow-empty
                                git push origin main
                            """
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build & Infra update complete!"
        }
        failure {
            echo "❌ Pipeline failed!"
        }
    }
}