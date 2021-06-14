FROM maven:3.8-adoptopenjdk-11 as builder
WORKDIR /usr/src/app
COPY src ./src
COPY pom.xml .
RUN ["mvn", "install"]

FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY --from=builder /usr/src/app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]