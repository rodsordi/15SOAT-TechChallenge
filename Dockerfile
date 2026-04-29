FROM amazoncorretto:25

COPY application/target/api-garage.application-0.0.1-SNAPSHOT.jar api-garage.jar

ENTRYPOINT ["java","-jar","/api-garage.jar"]