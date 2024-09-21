# Stage 1: Build the application using Maven with Eclipse Temurin JDK 11
FROM maven:3.8.6-eclipse-temurin-11 AS build

# Set the working directory for the build stage
WORKDIR /app

# Copy the Maven project file and source code into the container
COPY pom.xml .
COPY src ./src

# Build the project, using multiple threads and skipping tests
RUN mvn -T 2C -q clean package -DskipTests

# Stage 2: Create a minimal runtime image using OpenJDK 11 JRE
FROM openjdk:11-jre-slim

# Set the working directory for the runtime stage
WORKDIR /app

# Copy all built files from the build stage to the runtime stage
COPY --from=build /app /app

# Set the command to run the application
CMD ["java", "-jar", "/app/target/helm-plugin-0.0.1-SNAPSHOT.jar"]
