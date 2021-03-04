pipeline {
    agent any

    environment {
        // Maven tool id
        mavenInstallation = 'Maven3'
    }

    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }
        stage("Build/Test Server") {
            steps {
                script {

                        withMaven(maven: mavenInstallation) {
                            sh "mvn clean verify -U"
                        }

                }
            }
        }
    }
}