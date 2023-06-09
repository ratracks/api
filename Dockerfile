FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 4200

COPY --from=build /build/libs/ratracks-api-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]