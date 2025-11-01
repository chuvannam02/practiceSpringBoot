pipeline {
  agent any
  stages {
    stage('Checkout Code') {
      steps{
        git 'https://github.com/chuvannam02/practiceSpringBoot.git'
      }
    }

    stage('Build') {
      steps{
        sh 'echo "Building the application"'
      }
    }

    stage('Tests') {
      steps{
         sh 'echo "Running Tests"'
      }
    }

    stage('Deploy') {
      steps{
        sh 'step "Deploying"'
      }
    }
  }
}
