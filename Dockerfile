FROM ubuntu:18.04 AS build
RUN apt-get update && sudo apt-get install java-common -y
RUN dpkg --install java-21-amazon-corretto-jdk_21.0.3.9-1_amd64.deb
COPY . .
RUN ./mvnw clean package

FROM amazoncorretto:21
EXPOSE 8080
COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
