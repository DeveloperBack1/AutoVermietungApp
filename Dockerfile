
FROM openjdk:17-jdk

COPY target/autovermietungapp.jar .

COPY src/main/resources/application-dev.yaml /config/application-dev.yaml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "autovermietungapp.jar", "--spring.config.location=file:/config/application-dev.yaml"]
