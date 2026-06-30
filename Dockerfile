FROM amazoncorretto:25

WORKDIR /app

COPY application/target/api-garage.application-0.0.1-SNAPSHOT.jar api-garage.jar

RUN groupadd -r appgroup \
    && useradd -r -g appgroup -m appuser \
    && chown -R appuser:appgroup /app

USER appuser

ENTRYPOINT ["java","-jar","/api-garage.jar"]