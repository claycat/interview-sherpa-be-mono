FROM eclipse-temurin:21 AS build

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

COPY common common
COPY api-service api-service

RUN chmod +x ./gradlew

RUN ./gradlew :api-service:bootJar --no-daemon

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/api-service/build/libs/api-service-*.jar /app/api-service.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app/api-service.jar"]
