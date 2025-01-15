FROM maven:3.5-jdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTest

FROM amazoncorretto:11
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
#variaveis de ambiente
ARG RABBITMQ_HOST=host
ARG EUREKASERVER_HOST=host

ENTRYPOINT java -jar app.jar
