package com.allan_dev.instrutorFacil.domain.exceptions;

public class ExerciseAlreadyExists extends RuntimeException {
    public ExerciseAlreadyExists() {
        super("Exercício já cadastrado.");
    }
}
