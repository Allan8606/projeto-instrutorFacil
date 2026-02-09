package com.allan_dev.instrutorFacil.exceptions;

public class ExerciseNotFound extends RuntimeException {
    public ExerciseNotFound() {
        super("Exercício não encontrado.");
    }
}
