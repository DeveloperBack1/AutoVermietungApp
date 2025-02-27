# Use the official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the container
COPY target/autovermietungapp.jar /app/autovermietungapp.jar

# Copy Spring configuration files (e.g., application.properties)
COPY src/main/resources/application.properties /app/application.properties

# Use ADD instead of curl to download wait-for-it.sh
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Expose the application port
EXPOSE 8080

# Set environment variables for MySQL (password removed)
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DB_NAME=autovermietungapp
ENV MYSQL_USER=root

# Command to start the application after the database is available
CMD ["/usr/local/bin/wait-for-it.sh", "localhost:3306", "--", "java", "-jar", "/app/autovermietungapp.jar"]
