# Banking-Apllication-By-Using-Hibernate-Springboot-and-JPA
This repository contains a Java-based bank system with Spring Boot, Hibernate, and Jakarta Persistence. It includes User and Account entities and supports creating, depositing, withdrawing, transferring, and viewing transaction history. The MVC architecture includes a RESTful API, error handling, and a detailed README.



##building and deploying maven project

mvn clean install
java -jar {jarFileName} : java -jar bank-0.0.1-SNAPSHOT.jar


## Create and deploy docker image

1. Create file named as Dockerfile in project folder (specify the path of jar file in Dockerfile) 
2. in terminal locate the docker file and run "Docker build -t bankms ." in same directory
3. then can check the repo is created or not by "Docker images" command
4. run the docker image by "Docker run -p 8099:8080 bankms ."

now these microservices are running on docker. and we can access all the endpoints on port 8099.
