-- Insert test data

-- Insert users
INSERT INTO users (user_name, user_email)
VALUES ('John Doe', 'john@example.com'),
       ('Jane Smith', 'jane@example.com'),
       ('Alice Johnson', 'alice@example.com'),
       ('Bob Brown', 'bob@example.com');

-- Insert roles
INSERT INTO roles (role_name)
VALUES ('ADMIN'),
       ('USER'),
       ('MANAGER'),
       ('GUEST');

-- Assign roles to users
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 4);


-- Insert authorities
INSERT INTO authorities (authority_name)
VALUES ('READ_PRIVILEGES'),
       ('WRITE_PRIVILEGES'),
       ('DELETE_PRIVILEGES'),
       ('EXECUTE_PRIVILEGES');

-- Assign authorities to roles
INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 3);

-- Insert cars
INSERT INTO cars (car_model, car_brand, car_price_per_day)
VALUES ('X5', 'BMW', 100.00),
       ('Corsa', 'OPEL', 50.00),
       ('A4', 'AUDI', 80.00),
       ('Model 3', 'TESLA', 120.00);

--Insert rentals
INSERT INTO rentals (start_date, end_date, total_cost, user_id, car_id)
VALUES
    ('2024-02-01', '2024-02-05', 200.50, 1, 2),
    ('2024-02-10', '2024-02-15', 350.75, 2, 3),
    ('2024-03-05', '2024-03-10', 180.25, 3, 1),
    ('2024-05-20', '2024-05-25', 275.50, 2, NULL), -- Машина удалена (NULL)
    ('2024-06-15', '2024-06-20', 500.00, NULL, 3);