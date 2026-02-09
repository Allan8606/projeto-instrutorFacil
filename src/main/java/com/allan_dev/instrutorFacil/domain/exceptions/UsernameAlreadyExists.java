package com.allan_dev.instrutorFacil.domain.exceptions;

public class UsernameAlreadyExists extends RuntimeException {
    public UsernameAlreadyExists() {
        super("Este nome de usuário(login) já está sendo utilizado.");
    }
}
