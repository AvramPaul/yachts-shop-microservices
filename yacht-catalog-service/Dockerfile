# ── Stage 1: Build ──────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src

# Copiem doar pom.xml întâi pentru a refolosi cache-ul Docker pentru dependențe
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiem sursele și compilăm
COPY src ./src
RUN mvn clean package -DskipTests

# ── Stage 2: Runtime ─────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

# Rulăm ca utilizator non-root pentru securitate (cum ai învățat la .NET)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Luăm fișierul .jar rezultat din prima etapă
COPY --from=build /src/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]