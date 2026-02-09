package com.allan_dev.instrutorFacil.domain.exceptions;

public class ExerciseNotRegistered extends RuntimeException {
    public ExerciseNotRegistered() {
        super("Um ou mais exercícios não foram registrados.");
    }
}
