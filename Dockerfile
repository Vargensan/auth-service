# Etap 1: Budowanie aplikacji
FROM gradle:8.13-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean :auth-app:bootJar -x test --no-daemon

# Etap 2: Uruchamianie aplikacji
FROM eclipse-temurin:21-jre
WORKDIR /app

# narzędzia potrzebne w entrypoincie
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl netcat-openbsd \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/auth-app/build/libs/*.jar app.jar
COPY entrypoint.sh /app/entrypoint.sh

RUN chmod +x /app/entrypoint.sh

EXPOSE 8080 5005

ENTRYPOINT ["/app/entrypoint.sh"]
