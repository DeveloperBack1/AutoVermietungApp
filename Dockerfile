 #Use the official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Install curl to download the wait-for-it.sh script
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copy the jar file with the application into the container
COPY target/autovermietungapp.jar /app/autovermietungapp.jar

# Copy Spring configuration files (e.g., application.properties)
COPY src/main/resources/application.properties /app/application.properties

RUN curl -o /usr/local/bin/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /usr/local/bin/wait-for-it.sh

# Expose the application port
EXPOSE 8080

# Set environment variables for MySQL
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DB_NAME=autovermietungapp
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root

# Command to start the application after the database is available
CMD ["/usr/local/bin/wait-for-it.sh", "localhost:3306", "--", "java", "-jar", "/app/autovermietungapp.jar"]