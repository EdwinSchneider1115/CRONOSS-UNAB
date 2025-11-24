FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY dist/CRONOSS.jar app.jar
COPY lib/ lib/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
