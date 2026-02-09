package com.allan_dev.instrutorFacil.infra.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ExerciseResponse(
        Long exerciseId,
        String name,
        String description,
        String link,
        List<Long> trainingExercises
) {
}
