CREATE TABLE training (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    day_of_week VARCHAR(20),
    observations TEXT,

    CONSTRAINT fk_training_user
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE
);
