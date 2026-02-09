CREATE TABLE training_exercise (
    training_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,

    CONSTRAINT pk_training_exercise
        PRIMARY KEY (training_id, exercise_id),

    CONSTRAINT fk_training_exercise_training
        FOREIGN KEY (training_id)
        REFERENCES training (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_training_exercise_exercise
        FOREIGN KEY (exercise_id)
        REFERENCES exercise (exercise_id)
        ON DELETE CASCADE
);
