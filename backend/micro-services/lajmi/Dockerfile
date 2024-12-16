FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/lajmi-0.0.1-SNAPSHOT.jar lajmi.jar
EXPOSE 8090
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar", "lajmi.jar"]
