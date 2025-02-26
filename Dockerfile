# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем jar-файл приложения в контейнер
COPY target/autovermietungapp.jar /app/autovermietungapp.jar

# Копируем конфигурационные файлы Spring (например, application.properties)
COPY src/main/resources/application.properties /app/application.properties

# Используем ADD вместо curl для загрузки wait-for-it.sh
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Открываем порт приложения
EXPOSE 8080

# Устанавливаем переменные окружения для MySQL (пароль удален)
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DB_NAME=autovermietungapp
ENV MYSQL_USER=root

# Команда для запуска приложения после доступности базы данных
CMD ["/usr/local/bin/wait-for-it.sh", "localhost:3306", "--", "java", "-jar", "/app/autovermietungapp.jar"]
