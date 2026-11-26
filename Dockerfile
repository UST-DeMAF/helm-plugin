# Stage 1: Build the application using Maven with Eclipse Temurin JDK 11
FROM maven:3.8.6-eclipse-temurin-11 AS build

# Set the working directory for the build stage
WORKDIR /app

# Copy the Maven project file and source code into the container
COPY pom.xml .
COPY src ./src

# Build the project, using multiple threads and skipping tests
RUN mvn -T 2C -q clean package -DskipTests

# Stage 2: Create a minimal runtime image using the Eclipse Temurin 11 JRE
# The image already contains curl
FROM eclipse-temurin:11-jre

# Set the working directory for the runtime stage
WORKDIR /app

# Copy all built files from the build stage to the runtime stage
COPY --from=build /app /app

# Install curl for downloading the Helm install script

# Download the Helm install script, make it executable and run it
RUN curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 && \
	chmod 700 get_helm.sh && \
	./get_helm.sh

# Set the command to run the application
CMD ["java", "-jar", "/app/target/helm-plugin-0.2.0-SNAPSHOT.jar"]
