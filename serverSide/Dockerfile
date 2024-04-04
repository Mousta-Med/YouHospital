FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /serverside
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20
WORKDIR /serverside
COPY --from=build /serverside/target/serverSide-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "serverSide-0.0.1-SNAPSHOT.jar"]
