pipeline {
    agent any

    stages {
        stage('CHECKOUT CODE') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AsmaBouziri/ProjetJavaJenkins']])
            }
        }

        stage('Install Maven Build Tool') {
            steps {
                bat 'curl -O https://dlcdn.apache.org/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.zip'
                bat 'tar -xf apache-maven-3.9.8-bin.zip'
                bat 'dir C:\\apache-maven-3.9.8'
            }
        }

        stage('Maven Clean Compile') {
            steps {
                dir('C:\\Users\\Asma\\Desktop\\ProjetJavaJenkins') {
                    bat 'C:\\apache-maven-3.9.8\\bin\\mvn clean'
                    bat 'C:\\apache-maven-3.9.8\\bin\\mvn compile'
                }
            }
        }

        stage('Tests - JUnit/Mockito') {
            steps {
                dir('C:\\Users\\Asma\\Desktop\\ProjetJavaJenkins') {
                    bat 'C:\\apache-maven-3.9.8\\bin\\mvn test'
                }
            }
        }
        
        

        stage('Build package') {
            steps {
                dir('C:\\Users\\Asma\\Desktop\\ProjetJavaJenkins') {
                    bat 'C:\\apache-maven-3.9.8\\bin\\mvn package'
                }
            }
        }

        stage('Rapport JaCoCo') {
            steps {
                bat 'mvn test'
                bat 'mvn jacoco:report'
            }
        }

        stage('JaCoCo coverage report') {
            steps {
                step([$class: 'JacocoPublisher',
                      execPattern: '**/target/jacoco.exec',
                      classPattern: '**/classes',
                      sourcePattern: '**/src',
                      exclusionPattern: '*/target/**/,**/*Test*,**/*_javassist/**'
                ])
            }
        }

        stage("SonarQube Analysis") {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    bat 'C:\\apache-maven-3.9.8\\bin\\mvn sonar:sonar'
                }
            }
        }

        stage('Upload Artifacts To Nexus') {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if (artifactExists) {
                        echo "*** ok";
                        nexusArtifactUploader(
                            nexusVersion: "nexus3",
                            protocol: "http",
                            nexusUrl: "localhost:8081",
                            groupId: "com.example",
                            version: "1.0-SNAPSHOT",
                            repository: "ProjetJavaJenkins",
                            credentialsId: "Nexus-Credentials",
                            artifacts: [
                                [artifactId: "test-jenkins",
                                 classifier: '',
                                 file: "pom.xml",
                                 type: "pom"],
                                [artifactId: "pom.artifactId",
                                 classifier: '',
                                 file: "pom.xml",
                                 type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }

        
 stage('Build Docker Image') {
            steps {
                script {
                    // Assurez-vous que vous êtes dans le bon répertoire
                   dir('C:\\Users\\Asma\\Desktop\\ProjetJavaJenkins') {
                        // Construire l'image Docker
                        bat "docker build -t asmabouziri/projetjavajenkins:123 ."
                    }
                }
            }
        }
        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    withDockerRegistry(credentialsId: '588c9382-c2fc-4389-9934-5b27f0237248') {
                        bat "docker push asmabouziri/projetjavajenkins:123"
                    }
                }
            }
        }


        stage('Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deploymentservice.yaml', kubeconfigId: 'k8sconfigpwd')
            }
        }
        }
        

        stage('Email Notification') {
            steps {
                mail bcc: '',
                     body: '''Stage: GIT Pull
 - Pulling from Git...

Stage: Maven Clean Compile
 - Building project...

Stage: Test - JUNIT/MOCKITO
 - Testing project...

Stage: JaCoCo Report
 - Generating JaCoCo Report...

Stage: SonarQube Analysis
 - Running Sonarqube analysis...

Stage: Deploy to Nexus
 - Deploying to Nexus...

Stage: Build Docker Image
 - Building Docker image for the application...

Stage: Push Docker Image
 - Pushing Docker image to Docker Hub...

Stage: Docker Compose
 - Running Docker Compose...

Final Report: The pipeline has completed successfully. No action required.''',
                     cc: '',
                     from: '',
                     replyTo: '',
                     subject: 'Succes de la pipeline',
                     to: 'asmabouziri299@gmail.com'
            }
        }
    }
    
    post {
        success {
            script {
                mail bcc: '',
                     body: 'Build success - The pipeline has completed successfully.',
                     cc: '',
                     from: '',
                     replyTo: '',
                     subject: 'Build success in Jenkins',
                     to: 'asmabouziri299@gmail.com'
            }
        }
        failure {
            script {
                mail bcc: '',
                     body: 'Build failed - There was an issue with the pipeline.',
                     cc: '',
                     from: '',
                     replyTo: '',
                     subject: 'Build failed in Jenkins',
                     to: 'asmabouziri299@gmail.com'
            }
        }
    }
}