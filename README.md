# AutoVermietungApp

## Description

AutoVermietungApp is a car rental service application built using modern Java technologies. The project is designed to
handle user authentication, role management, and car rental transactions. It includes an API documented with Swagger and
utilizes various Spring Boot modules for efficiency and scalability.

## Technology Stack

- **Java 17**
- **Spring Framework**
- **Spring Security**
- **Lombok**
- **JPA (Hibernate)**
- **MySQL Database**
- **H2 Database**(Database Testing)
- **Liquibase** (Database versioning)
- **MockMvc** (Testing framework)
- **Maven** (Build management)
- **Docker** (Containerization)
- **SLF4J** (Logging framework)
- **Swagger** (API documentation)
- **Aspect Logging** (Logging aspects using Spring AOP)

## Features

- User authentication and authorization using JWT.
- Role-based access control (ADMIN, USER, MANAGER).
- Secure password storage using hashing.
- Car rental functionality with pricing calculation.
- Database version control with Liquibase.
- API documentation with Swagger.
- Logging implementation with SLF4J and AOP.

## Database Schema

The database consists of the following tables:

### **users**

| Column        | Type         | Description                     |
|---------------|--------------|---------------------------------|
| user_id       | INT (PK)     | Unique identifier for each user |
| user_name     | VARCHAR(255) | Full name of the user           |
| user_email    | VARCHAR(255) | Email address (unique)          |
| user_password | VARCHAR(255) | Hashed password                 |

### **roles**

| Column    | Type         | Description                      |
|-----------|--------------|----------------------------------|
| role_id   | INT (PK)     | Unique identifier for each role  |
| role_name | VARCHAR(255) | Role name (ADMIN, USER, MANAGER) |

### **authorities**

| Column         | Type         | Description                          |
|----------------|--------------|--------------------------------------|
| authority_id   | INT (PK)     | Unique identifier for each authority |
| authority_name | VARCHAR(255) | Name of the authority (permissions)  |

### **users_roles** (Mapping Table)

| Column  | Type | Description                             |
|---------|------|-----------------------------------------|
| user_id | INT  | Foreign key referencing `users.user_id` |
| role_id | INT  | Foreign key referencing `roles.role_id` |

### **roles_authorities** (Mapping Table)

| Column       | Type | Description                                        |
|--------------|------|----------------------------------------------------|
| role_id      | INT  | Foreign key referencing `roles.role_id`            |
| authority_id | INT  | Foreign key referencing `authorities.authority_id` |

### **cars**

| Column            | Type          | Description                    |
|-------------------|---------------|--------------------------------|
| car_id            | INT (PK)      | Unique identifier for each car |
| car_model         | VARCHAR(255)  | Model name of the car          |
| car_brand         | VARCHAR(255)  | Brand of the car               |
| car_price_per_day | DECIMAL(10,2) | Rental price per day           |

### **rentals**

| Column            | Type          | Description                             |
|-------------------|---------------|-----------------------------------------|
| rental_id         | INT (PK)      | Unique identifier for each rental       |
| rental_start_date | DATE          | Start date of the rental                |
| rental_end_date   | DATE          | End date of the rental                  |
| rental_total_cost | DECIMAL(10,2) | Total rental cost                       |
| user_id           | INT (FK)      | Foreign key referencing `users.user_id` |
| car_id            | INT (FK)      | Foreign key referencing `cars.car_id`   |

## Setup and Running the Project

### **Prerequisites**

- Java 17
- Maven
- Docker

### **Installation Steps**

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/AutoVermietungApp.git
   ```
2. Navigate to the project directory:
   ```sh
   cd AutoVermietungApp
   ```
3. Build the project with Maven:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   
   ```
5. Access the API documentation:
    - Swagger UI: `http://localhost:8080/swagger-ui.html`

### **Running with Docker**

1. Build the Docker image:
   ```sh
   docker build -t autovermietungapp .
   ```
2. Run the container:
   ```sh
   docker run -p 8080:8080 autovermietungapp
   ```

## Testing

- The application includes unit and integration tests using **Mockito**.
- Run tests with:
  ```sh
  mvn test
  ```

## Logging

- The application uses **SLF4J** for logging.
- Aspect-oriented logging is enabled using **Spring AOP**.

---

### Contributors

- **Max Schneider** (Lead Developer)