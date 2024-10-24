FROM openjdk:21-jdk-slim

ARG JAR_FILE=target/campussphere-api-0.0.1.jar

COPY ${JAR_FILE} campussphere-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "campussphere-api.jar"]