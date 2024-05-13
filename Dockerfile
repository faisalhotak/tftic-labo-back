FROM ubuntu:18.04 AS build

# Install necessary dependencies
RUN apt-get update && apt-get install -y wget

# Download and install Amazon Corretto JDK
RUN wget https://corretto.aws/downloads/latest/amazon-corretto-21-x64-linux-jdk.deb
RUN dpkg -i java-21-amazon-corretto-jdk_21.0.3.9-1_amd64.deb

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-21-amazon-corretto

# Copy the application source code
COPY . /app
WORKDIR /app

# Build the application
RUN ./mvnw clean package

# Final stage: Run the application
FROM amazoncorretto:21
EXPOSE 8080
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]
