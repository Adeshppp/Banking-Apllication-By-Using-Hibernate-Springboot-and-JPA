#importing docker image (openjdkL:17-jdk-alpine) which has linux and jdk installed
FROM openjdk:17-jdk-slim
#specifying location of jar file
ARG JAR_FILE=target/*.jar
#creating docker file named as app.jar
COPY ./target/bank-0.0.1-SNAPSHOT.jar app.jar
#specifying the command to run the image java -jar javeFileName
ENTRYPOINT ["java","-jar","/app.jar"]
