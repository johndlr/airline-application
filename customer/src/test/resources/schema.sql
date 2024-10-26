CREATE TABLE IF NOT EXISTS addresses (
    address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(45) NOT NULL
);

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





