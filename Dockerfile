FROM debian:latest

RUN apt-get update \
    && apt-get upgrade -y \
    && apt-get install --no-install-recommends -y default-jdk maven curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /app/target
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

CMD java -jar target/helm-plugin-0.0.1-SNAPSHOT.jar