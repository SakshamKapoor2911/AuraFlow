# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar file (replace with your actual jar name if needed)
COPY target/*.jar app.jar

# Expose the port (match server.port in application.properties)
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]