FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/user-0.0.1-SNAPSHOT.jar user.jar
EXPOSE 8111
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar", "user.jar"]
