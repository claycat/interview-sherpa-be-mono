FROM eclipse-temurin:21 AS build

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

COPY common common
COPY ai-service ai-service

RUN chmod +x ./gradlew

RUN ./gradlew :ai-service:bootJar --no-daemon

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/ai-service/build/libs/ai-service-*.jar /app/ai-service.jar

EXPOSE 8030

ENTRYPOINT ["java", "-jar", "/app/ai-service.jar"]
