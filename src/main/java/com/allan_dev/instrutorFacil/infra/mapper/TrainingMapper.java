package com.allan_dev.instrutorFacil.infra.mapper;


import com.allan_dev.instrutorFacil.infra.dto.request.TrainingRequest;
import com.allan_dev.instrutorFacil.infra.dto.response.TrainingResponse;
import com.allan_dev.instrutorFacil.domain.entity.ExerciseEntity;
import com.allan_dev.instrutorFacil.domain.entity.TrainingEntity;
import com.allan_dev.instrutorFacil.domain.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public class TrainingMapper {



    public static TrainingEntity toEntity(
            TrainingRequest request,
            UserEntity user
    ) {

        List<ExerciseEntity> list = request.exerciseIds()
                .stream()
                .map(id -> ExerciseEntity
                        .builder()
                        .exerciseId(id)
                        .build()
                )
                .toList();

        return TrainingEntity.builder()
                .user(user)
                .exercises(list)
                .dayOfWeek(request.dayOfWeek())
                .observations(request.observations())
                .build();
    }



    public static TrainingResponse toResponse(TrainingEntity entity){

        String name = entity.getUser().getName();

        List<String> listNameExercises = entity.getExercises()
                .stream()
                .map(ExerciseEntity::getName)
                .toList();

        return TrainingResponse
                .builder()
                .id(entity.getId())
                .nameUser(name)
                .nameExercise(listNameExercises)
                .dayOfWeek(entity.getDayOfWeek())
                .observations(entity.getObservations())
                .build();
    }

}
