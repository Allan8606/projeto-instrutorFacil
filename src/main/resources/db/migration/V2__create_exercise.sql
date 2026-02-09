CREATE TABLE exercise (
    exercise_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(150),
    link TEXT
);
