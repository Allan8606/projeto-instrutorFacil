package com.allan_dev.instrutorFacil.infra.dto.response;

import lombok.Builder;

import java.util.List;


@Builder
public record UserResponse(
        Long id,
        String name,
        String login,
        List<String> trainingExercises
) {
}
