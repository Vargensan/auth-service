#!/usr/bin/env sh
set -e

echo "Czekam na bazę danych (auth-db:5432)..."
until nc -z auth-db 5432 >/dev/null 2>&1; do
  sleep 2
done

echo "Czekam na Kafka (kafka:9092)..."
until nc -z kafka 9092 >/dev/null 2>&1; do
  sleep 2
done

echo "Czekam na Schema Registry (schema-registry:8081)..."
until curl -sSf http://schema-registry:8081/subjects >/dev/null 2>&1; do
  sleep 2
done

echo "Wszystkie zależności dostępne. Startuję AuthService..."
exec java \
  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
  -jar app.jar
