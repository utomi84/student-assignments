# Alap image: OpenJDK 21
FROM eclipse-temurin:21-jdk

# Mappa, ahol dolgozunk a konténeren belül
WORKDIR /app

# JAR fájl bemásolása
COPY target/student-assignments-0.0.1-SNAPSHOT.jar app.jar

# Konfigurációs fájl bemásolása
COPY src/main/resources/application.properties /app/application.properties

# Port, amin az app fut
EXPOSE 8080

# Alkalmazás indítása a saját konfiggal
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application.properties"]
