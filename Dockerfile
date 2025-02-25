# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Устанавливаем curl для загрузки скрипта wait-for-it.sh
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Копируем jar файл с приложением в контейнер
COPY target/autovermietungapp.jar /app/autovermietungapp.jar

# Копируем конфигурационные файлы Spring (например, application.properties)
COPY src/main/resources/application.properties /app/application.properties

# Скачиваем wait-for-it.sh из репозитория на GitHub
RUN curl -o /usr/local/bin/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /usr/local/bin/wait-for-it.sh

# Устанавливаем порт приложения
EXPOSE 8080

# Устанавливаем переменные окружения для MySQL
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DB_NAME=autovermietungapp
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root

# Команда для запуска приложения после того, как база данных будет доступна
CMD ["/usr/local/bin/wait-for-it.sh", "localhost:3306", "--", "java", "-jar", "/app/autovermietungapp.jar"]




