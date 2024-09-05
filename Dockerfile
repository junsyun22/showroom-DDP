FROM ubuntu:latest
LABEL authors="junsyun"

ENTRYPOINT ["top", "-b"]
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file to the container
COPY build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 12450

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
