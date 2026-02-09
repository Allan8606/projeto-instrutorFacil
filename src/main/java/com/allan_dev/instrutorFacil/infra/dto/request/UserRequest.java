package com.allan_dev.instrutorFacil.infra.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRequest(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O login é obrigatório")
        String login,
        @NotBlank(message = "A senha é obrigatório")
        String password,
        List<Long> trainingExercises
) {
}
