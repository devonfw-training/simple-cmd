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

                try {
                    withMaven(maven: mavenInstallation) {
                        sh "mvn clean verify -U"
                    }
                } catch (err) {
                    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: false])
                    throw err
                }

            }
        }
    }
}