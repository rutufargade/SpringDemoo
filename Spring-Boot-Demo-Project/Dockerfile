FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY target/spring-boot-docker.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]