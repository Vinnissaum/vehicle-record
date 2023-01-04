CREATE TABLE vehicle (
    id UUID NOT NULL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    production_year NUMERIC(19),
    description TEXT,
    is_sold BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    ext JSONB
);

CREATE INDEX vehicle_ext ON vehicle USING GIN (ext);