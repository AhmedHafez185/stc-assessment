# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/assessment-0.0.1-SNAPSHOT.jar assessment-app.jar
EXPOSE 9095
ENTRYPOINT ["java","-jar","/assessment-app.jar"]

