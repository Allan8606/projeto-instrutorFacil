package com.allan_dev.instrutorFacil.exceptions;

public class ExerciseAlreadyExists extends RuntimeException {
    public ExerciseAlreadyExists() {
        super("Exercício já cadastrado.");
    }
}
