FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY dist/CRONOSS.jar app.jar
COPY lib/ lib/

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "app.jar:lib/*", "javaapplication1.PaginaPrincipalReal"]

