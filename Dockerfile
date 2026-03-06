# Stage 1: Build a temporary image just to get the JAR filename
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/*.jar ./

ENV JAVA_OPTS="-Xmx512m"


# Aqui usamos o nome completo do jar principal no ENTRYPOINT
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
