FROM openjdk:21-jdk-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src src

COPY mvnw .
COPY .mvn .mvn

RUN ls -l /app && ls -l /app/.mvn && ls -l /app/mvnw

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=build /app/target/bio-num-tech-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]