FROM eclipse-temurin:17-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/plano-de-formacao-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "plano-de-formacao-0.0.1-SNAPSHOT.jar"]

