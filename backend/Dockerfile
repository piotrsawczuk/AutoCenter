FROM maven:3.6.2-jdk-8-slim AS build
RUN mkdir -p /backend
WORKDIR /backend
COPY pom.xml /backend
COPY src /backend/src
RUN mvn --quiet --file pom.xml clean package

FROM openjdk:8-alpine
COPY --from=build /backend/target/*.jar AutoCenter.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","AutoCenter.jar"]
