FROM debian:latest

RUN apt-get update \
    && apt-get upgrade -y \
    && apt-get install -y default-jdk maven curl \
    && apt-get autoremove -y \
    && apt-get autoclean -y

RUN mkdir -p /app/target
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

CMD java -jar target/helm-plugin-0.0.1-SNAPSHOT.jar