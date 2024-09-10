FROM alpine:latest

RUN apk upgrade --no-cache \
    && apk add --no-cache bash curl gcompat libstdc++ maven openjdk11

RUN mkdir -p /app/target
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

CMD java -jar target/helm-plugin-0.0.1-SNAPSHOT.jar
