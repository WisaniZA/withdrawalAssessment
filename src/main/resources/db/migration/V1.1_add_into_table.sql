
INSERT INTO investor (first_name, last_name, date_of_birth, address, mobile_number, email)
VALUES ('John', 'Doe', '1990-01-01', '123 Main St', '1234567890', 'john.doe@example.com');

INSERT INTO product (investor_id, product_type, balance)
VALUES (1, 'RETIREMENT', 500000.00),
       (1, 'SAVINGS', 36000.00);