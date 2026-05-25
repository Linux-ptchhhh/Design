# --- Stage 1: Build & Package the Application ---
FROM eclipse-temurin:25-jdk-jammy AS builder
WORKDIR /build

# Copy maven wrapper assets first to optimize layer caching
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copy source code and build the production JAR
COPY src src
RUN ./mvnw clean package -DskipTests

# --- Stage 2: Final Lightweight Runtime Room ---
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app

# Steal only the finished executable .jar file from Stage 1
COPY --from=builder /build/target/*.jar app.jar

# Expose Spring Boot's default port
EXPOSE 8080

# Configure production JVM flags for container environments
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
