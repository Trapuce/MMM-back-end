FROM eclipse-temurin:21

WORKDIR /app

COPY target/MMM-Back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]