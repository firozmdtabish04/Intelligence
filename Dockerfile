FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy Maven wrapper first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give permission
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy rest of project
COPY . .

# 🔥 FIX: re-apply permission after overwrite
RUN chmod +x mvnw

# Build project
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/*.jar"]