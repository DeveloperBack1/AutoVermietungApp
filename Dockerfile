FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем JAR файл
COPY target/autovermietungapp.jar .

# Копируем wait-for-it.sh
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Запуск с проверкой готовности базы
CMD ["/usr/local/bin/wait-for-it.sh", "db:5432", "--", "java", "-jar", "autovermietungapp.jar"]




