SET REFERENTIAL_INTEGRITY FALSE;

DELETE FROM rentals;
DELETE FROM cars;
DELETE FROM roles_authorities;
DELETE FROM authorities;
DELETE FROM users_roles;
DELETE FROM roles;
DELETE FROM users;

INSERT INTO users (user_name, user_email, user_password)
VALUES ('Alice Johnson', 'alice@example.com', '$2b$12$0Ms.Buv4JlQsaWOB3K.qe.1fWfM7.TBHVRTsgQR2Ug8uL31HR6qFa'),
       ('Bob Smith', 'bob@example.com', '$2b$12$0Ms.Buv4JlQsaWOB3K.qe.1fWfM7.TBHVRTsgQR2Ug8uL31HR6qFa'),
       ('Charlie Davis', 'charlie@example.com', '$2b$12$0Ms.Buv4JlQsaWOB3K.qe.1fWfM7.TBHVRTsgQR2Ug8uL31HR6qFa');

INSERT INTO roles (role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_MANAGER');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO authorities (authority_name)
VALUES ('READ_PRIVILEGES'),
       ('WRITE_PRIVILEGES'),
       ('DELETE_PRIVILEGES');

INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 2);

INSERT INTO cars (car_model, car_brand, car_price_per_day)
VALUES ('Model 3', 'TESLA', 120.00),
       ('A4', 'AUDI', 80.00),
       ('Corsa', 'OPEL', 50.00),
       ('X5', 'BMW', 100.00),
       ('Golf', 'VW', 55.00);

INSERT INTO rentals (rental_start_date, rental_end_date, rental_total_cost, user_id, car_id)
VALUES ('2024-02-01', '2024-02-05', 480.00, 1, 1),
       ('2024-02-03', '2024-02-07', 200.00, 2, 2),
       ('2024-02-10', '2024-02-15', 325.00, 3, 3),
       ('2024-02-12', '2024-02-14', 200.00, 1, 4),
       ('2024-02-15', '2024-02-20', 275.00, 2, 5);

SET REFERENTIAL_INTEGRITY TRUE;