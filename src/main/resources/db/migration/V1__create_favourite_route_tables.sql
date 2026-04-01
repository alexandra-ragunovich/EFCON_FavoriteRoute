CREATE TABLE saved_routes (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    origin_city VARCHAR(255) NOT NULL,
    origin_country VARCHAR(255) NOT NULL,
    destination_city VARCHAR(255) NOT NULL,
    destination_country VARCHAR(255) NOT NULL, --
    total_distance_km DOUBLE PRECISION NOT NULL,
    total_co2_kg DOUBLE PRECISION NOT NULL,
    total_duration_hours DOUBLE PRECISION NOT NULL,
    ai_advice_text TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'PLANNED',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE saved_route_segments (
    id SERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    step_order INT NOT NULL,
    from_city VARCHAR(255) NOT NULL,
    from_country VARCHAR(255) NOT NULL,
    to_city VARCHAR(255) NOT NULL,
    to_country VARCHAR(255) NOT NULL,
    transport_type VARCHAR(100) NOT NULL,
    total_duration_hours DOUBLE PRECISION NOT NULL,
    distance_km DOUBLE PRECISION NOT NULL,
    co2_emission_kg DOUBLE PRECISION NOT NULL,
    linked_post_id BIGINT,


    CONSTRAINT fk_saved_route FOREIGN KEY (route_id) REFERENCES saved_routes (id) ON DELETE CASCADE
);