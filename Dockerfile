# Используем Maven для сборки
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Используем OpenJDK для запуска приложения
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/AutoVermietungApp.jar app.jar

# Определяем переменные окружения
ENV DB_URL=jdbc:mysql://mysql:3306/autovermietungapp
ENV DB_USERNAME=root
ENV DB_PASS=Tausend1308

ENTRYPOINT ["java", "-jar", "app.jar"]




