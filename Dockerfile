# Use modern, supported Java image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy Maven wrapper & config first (better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give permission to mvnw
RUN chmod +x mvnw

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy full project
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the JAR (auto-detect jar name)
CMD ["sh", "-c", "java -jar target/*.jar"]