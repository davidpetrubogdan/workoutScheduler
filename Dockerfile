FROM openjdk:8-jdk-alpine
ADD build/libs/scheduler-0.0.1-SNAPSHOT.jar scheduler-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "scheduler-backend.jar"]