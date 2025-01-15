FROM maven:3.5-jdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTest

FROM amazoncorretto:11
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
ENTRYPOINT java -jar app.jar