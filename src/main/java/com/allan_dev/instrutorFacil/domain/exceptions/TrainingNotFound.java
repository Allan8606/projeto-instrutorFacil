package com.allan_dev.instrutorFacil.domain.exceptions;

public class TrainingNotFound extends RuntimeException {
    public TrainingNotFound() {
        super("Treino não encontrado.");
    }
}
