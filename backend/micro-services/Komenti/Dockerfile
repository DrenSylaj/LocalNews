FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Komenti-0.0.1-SNAPSHOT.jar Komenti.jar
EXPOSE 8071
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "Komenti.jar"]
