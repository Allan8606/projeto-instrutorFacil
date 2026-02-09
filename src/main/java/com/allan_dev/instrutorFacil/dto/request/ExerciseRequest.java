package com.allan_dev.instrutorFacil.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ExerciseRequest(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        String description,
        String link,
        List<Long> trainingExercises
) {
}
