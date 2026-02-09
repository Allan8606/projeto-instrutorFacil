package com.allan_dev.instrutorFacil.infra.dto.response;


import lombok.Builder;

import java.time.DayOfWeek;
import java.util.List;

@Builder
public record TrainingResponse(
        Long id,
        String nameUser,
        List<String> nameExercise,
        DayOfWeek dayOfWeek,
        String observations
) {
}
