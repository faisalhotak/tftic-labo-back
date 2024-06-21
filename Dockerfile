FROM openjdk:21-slim AS build

WORKDIR /app

ARG PROFILE=prod

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY controller controller
COPY service service
COPY repository repository
COPY domain domain
COPY config config
COPY common common

RUN ./mvnw install -DskipTests

FROM openjdk:21-slim AS deploy

ARG DEPENDENCY=/app/controller/target

ARG JAR_NAME="controller-0.0.1-SNAPSHOT"

ARG PORT=8080

COPY --from=build ${DEPENDENCY}/${JAR_NAME}.jar app.jar

EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]
