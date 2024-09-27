CREATE TABLE reservations (
    reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_number VARCHAR(255) NOT NULL UNIQUE ,
    flight_number VARCHAR(255) NOT NULL UNIQUE,
    customer_mobile_number VARCHAR(255) NOT NULL UNIQUE,
    seat_number VARCHAR(255) NOT NULL UNIQUE,
    reservation_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(30) NOT NULL,
    updated_at TIMESTAMP NULL,
    updated_by VARCHAR(30) NULL
);

INSERT INTO
    reservations (reservation_number, flight_number, customer_mobile_number, seat_number, reservation_date, created_at, created_by, updated_at, updated_by)
VALUES
    ('RES1A2B3', 'FL1A2B3C', '1234567890', 'A12', '2023-10-01 08:00:00', CURRENT_TIMESTAMP, 'RESERVATION MS', NULL, NULL),
    ('RES5E6F7', 'FL4D5E6F', '0987654321', 'B34', '2023-10-02 09:00:00', CURRENT_TIMESTAMP, 'RESERVATION MS', NULL, NULL);