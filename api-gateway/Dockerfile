
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/api-gateway-1.0-SNAPSHOT.jar api-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
