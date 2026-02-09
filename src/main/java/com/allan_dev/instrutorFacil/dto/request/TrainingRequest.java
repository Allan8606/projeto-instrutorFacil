package com.allan_dev.instrutorFacil.dto.request;


import jakarta.validation.constraints.NotBlank;

import java.time.DayOfWeek;
import java.util.List;

public record TrainingRequest(
        @NotBlank(message = "É obrigatório passar o ID do usuário")
        Long userId,
        List<Long> exerciseIds,
        DayOfWeek dayOfWeek,
        String observations
) {
}
