FROM maven:3.9.3-eclipse-temurin-11 as build
WORKDIR /app

ADD pom.xml /app/
ADD interfaces/pom.xml /app/interfaces/
ADD models/pom.xml /app/models/
ADD persistence/pom.xml /app/persistence/
ADD service/pom.xml /app/service/
ADD webapp/pom.xml /app/webapp/
RUN mvn verify --fail-never

ADD interfaces /app/interfaces/
ADD models /app/models/
ADD persistence /app/persistence/
ADD service /app/service/
ADD webapp /app/webapp/
RUN mvn package

FROM eclipse-temurin:11-jdk-alpine
COPY --from=build /app/webapp/target/webapp-1.0-SNAPSHOT.jar /app/webapp.jar
ENTRYPOINT java -jar /app/webapp.jar
