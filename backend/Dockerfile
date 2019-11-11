FROM maven:3.6.2-jdk-11-slim AS build
RUN mkdir -p /backend
WORKDIR /backend
COPY pom.xml /backend
COPY src /backend/src
RUN mvn --quiet --file pom.xml clean package

FROM openjdk:11
COPY --from=build /backend/target/*.jar auto-center-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","auto-center-0.0.1.jar"]
