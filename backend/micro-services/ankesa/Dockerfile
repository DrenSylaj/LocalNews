FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/ankesa-0.0.1-SNAPSHOT.jar ankesa.jar
EXPOSE 8061
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar", "ankesa.jar"]
