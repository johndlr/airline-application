CREATE TABLE IF NOT EXISTS addresses (
    address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(45) NOT NULL
);

INSERT INTO
    addresses (street, city, state, zip_code)
VALUES
    ('123 Main St', 'Springfield', 'IL', '62701'),
    ('456 Elm St', 'Chicago', 'IL', '60601');


CREATE TABLE IF NOT EXISTS customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    address_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(30) NOT NULL,
    updated_at TIMESTAMP NULL,
    updated_by VARCHAR(30) NULL,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

INSERT INTO
    customers (name, last_name, email, mobile_number, address_id, created_at, created_by, updated_at, updated_by)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '1234567890', 1, CURRENT_TIMESTAMP, 'CUSTOMER MS', NULL, NULL),
    ('Jane', 'Smith', 'jane.smith@example.com', '0987654321', 2, CURRENT_TIMESTAMP, 'CUSTOMER MS', NULL, NULL);



