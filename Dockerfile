FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Gradle or Maven 빌드 jar 복사
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]