FROM amazoncorretto:25

WORKDIR /app

COPY application/target/api-garage.application-0.0.1-SNAPSHOT.jar api-garage.jar

RUN chown -R 1000 /app

USER 1000

ENTRYPOINT ["java","-jar","api-garage.jar"]