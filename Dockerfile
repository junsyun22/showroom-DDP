# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build context to the working directory
COPY target/showroom-DDP-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 12450

# Define the entry point for the Docker container
ENTRYPOINT ["java", "-jar", "app.jar"]
