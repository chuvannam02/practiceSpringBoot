pipeline {
    agent any

    environment {
        REGISTRY_URL = 'https://nexus-nginx:443'
        GIT_DEPLOY_REPO = 'https://github.com/chuvannam02/CI-CD.git'
        GIT_SOURCE_APP = 'https://github.com/chuvannam02/practiceSpringBoot.git'

        SONAR_HOST_URL = 'https://sonarcloud.io'
        SONAR_LOGIN = credentials('sonar-token')
        SONAR_ORG = 'chuvannam02'      
        SONAR_PROJECT_KEY = '387fd948e98b992ac4928e6a5a4169d32d5aa247'

        SLACK_CHANNEL = '#ci-cd'

        NEXUS_USER = credentials('nexus-user')
        NEXUS_PASS = credentials('nexus-pass')
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {
        stage('Init') {
            steps {
                script {
                    env.PIPELINE_START = System.currentTimeMillis()
                    echo "🚀 Pipeline started at ${new Date(env.PIPELINE_START.toLong())}"
                }
            }
        }

        stage('Checkout Source') {
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    echo "📦 Checking out source from ${GIT_SOURCE_APP}"
                    git branch: 'main', url: "${GIT_SOURCE_APP}"
                }
            }
        }

        stage('Generate Image Tag') {
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    script {
                        env.IMAGE_TAG = sh(script: "date +'%Y%m%d-%H%M%S'", returnStdout: true).trim()
                        echo "🏷️ Generated image tag: ${IMAGE_TAG}"
                    }
                }
            }
        }

        stage('Build & Test') {
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    echo "⚙️ Building Docker image..."
                    sh 'docker build -t my-app -f Dockerfile-prod .'
                }
            }
        }

        stage('SonarCloud Analysis') {
            when {
                expression {
                    // Chỉ chạy nếu Maven có sẵn trong Jenkins (không build bằng Docker)
                    sh(script: "command -v mvn >/dev/null 2>&1", returnStatus: true) == 0
                }
            }
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    echo "🔍 Running SonarCloud analysis..."
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh """
                            mvn clean verify sonar:sonar \
                              -Dsonar.organization=${SONAR_ORG} \
                              -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                              -Dsonar.host.url=${SONAR_HOST_URL} \
                              -Dsonar.token=${SONAR_TOKEN} \
                              -Dsonar.java.binaries=target/classes
                        """
                    }
                }
            }
        }

        // stage('Wait for Sonar Quality Gate') {
        //     steps {
        //         wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
        //             echo "🕒 Waiting for Sonar Quality Gate result..."
        //             timeout(time: 5, unit: 'MINUTES') {
        //                 waitForQualityGate abortPipeline: true
        //             }
        //         }
        //     }
        // }

        stage('Build & Push Docker Image') {
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    script {
                        echo "🐳 Building and pushing image to Nexus..."
                        def imageName = "my-app"
                        def imageFull = "${REGISTRY_URL}/${imageName}:${IMAGE_TAG}"

                        sh """
                            echo "${NEXUS_PASS}" | docker login ${REGISTRY_URL} -u "${NEXUS_USER}" --password-stdin
                            docker build -t ${imageFull} .
                            docker push ${imageFull}
                            docker logout ${REGISTRY_URL}
                        """
                        env.IMAGE_NAME = imageName
                    }
                }
            }
        }

        stage('Update Deploy Repo') {
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    script {
                        echo "📤 Updating deploy repo with new image tag ${IMAGE_TAG}"
                        sh """
                            rm -rf infra-deploy
                            git clone ${GIT_DEPLOY_REPO} infra-deploy
                            cd infra-deploy/k8s/my-app
                            sed -i "s|image: .*|image: ${REGISTRY_URL}/${IMAGE_NAME}:${IMAGE_TAG}|g" deployment.yaml
                            git config user.email "jenkins@ci.local"
                            git config user.name "jenkins"
                            git commit -am "Update image tag to ${IMAGE_TAG}" || echo "No changes to commit"
                            git push origin main
                        """
                    }
                }
            }
        }

        stage('Notify Slack') {
            steps {
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                    echo "📢 Sending Slack notification..."
                    slackSend channel: "${SLACK_CHANNEL}", message: "✅ *Build #${BUILD_NUMBER}* succeeded. Image tag: ${IMAGE_TAG}"
                }
            }
        }
    }
}
