FROM gradle:8.1.1-jdk17 as build
WORKDIR /app

#Download dependencies in layer
COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle
RUN gradle build || return 0 

#Compile JAR
COPY src /app/src
RUN gradle clean assemble


FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy JAR from build
COPY --from=build /app/build/libs/BankAPI-0.0.1-SNAPSHOT.jar /app

CMD java -jar ./BankAPI-0.0.1-SNAPSHOT.jar
