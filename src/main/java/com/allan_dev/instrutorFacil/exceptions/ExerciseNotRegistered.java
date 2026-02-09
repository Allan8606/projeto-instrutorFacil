package com.allan_dev.instrutorFacil.exceptions;

public class ExerciseNotRegistered extends RuntimeException {
    public ExerciseNotRegistered() {
        super("Um ou mais exercícios não foram registrados.");
    }
}
