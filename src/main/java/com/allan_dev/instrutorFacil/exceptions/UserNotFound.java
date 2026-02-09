package com.allan_dev.instrutorFacil.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("Usuário não encontrado");
    }
}
