FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/discovery-0.0.1-SNAPSHOT.jar discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "discovery.jar"]

