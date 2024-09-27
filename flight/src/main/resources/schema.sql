CREATE TABLE IF NOT EXISTS aircraft_types (
    aircraft_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL NOT NULL UNIQUE,
    manufacturer VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

INSERT INTO
    aircraft_types (model, manufacturer, capacity)
VALUES
    ('Boeing 737', 'Boeing', 189),
    ('Airbus A320', 'Airbus', 180),
    ('Boeing 777', 'Boeing', 396),
    ('Airbus A380', 'Airbus', 853),
    ('Embraer E190', 'Embraer', 114);

CREATE TABLE IF NOT EXISTS flights (
    flight_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(255) NOT NULL UNIQUE,
    origin VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(30) NOT NULL,
    updated_at TIMESTAMP NULL,
    updated_by VARCHAR(30) NULL,
    aircraft_type_id BIGINT NOT NULL,
    CONSTRAINT fk_flight_aircraft FOREIGN KEY (aircraft_type_id) REFERENCES aircraft_types(aircraft_type_id)
);

INSERT INTO
    flights (flight_number, origin, destination, departure_time, arrival_time, created_at, created_by, updated_at, updated_by, aircraft_type_id)
VALUES
    ('FL1A2B3C', 'New York', 'Los Angeles', '2023-10-01 08:00:00', '2023-10-01 11:00:00', CURRENT_TIMESTAMP, 'FLIGHT MS', NULL, NULL, 1),
    ('FL4D5E6F', 'Chicago', 'Miami', '2023-10-02 09:00:00', '2023-10-02 13:00:00', CURRENT_TIMESTAMP, 'FLIGHT MS', NULL, NULL, 2);
