package com.allan_dev.instrutorFacil.infra.mapper;


import com.allan_dev.instrutorFacil.infra.dto.request.ExerciseRequest;
import com.allan_dev.instrutorFacil.infra.dto.response.ExerciseResponse;
import com.allan_dev.instrutorFacil.domain.entity.ExerciseEntity;
import com.allan_dev.instrutorFacil.domain.entity.TrainingEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ExerciseMapper {

    public static ExerciseEntity toExerciseEntity(ExerciseRequest request){

        List<TrainingEntity> list = request.trainingExercises()
                .stream()
                .map(
                        trainingId -> TrainingEntity
                                .builder()
                                .id(trainingId)
                                .build()
                )
                .toList();

        return ExerciseEntity
                .builder()
                .name(request.name())
                .description(request.description())
                .link(request.link())
                .trainingExercises(list)
                .build();
    }




    public static ExerciseResponse toExerciseResponse(ExerciseEntity entity){

        List<Long> list = entity.getTrainingExercises()
                .stream()
                .map(TrainingEntity::getId)
                .toList();


        return ExerciseResponse
                .builder()
                .exerciseId(entity.getExerciseId())
                .name(entity.getName())
                .description(entity.getDescription())
                .link(entity.getLink())
                .trainingExercises(list)
                .build();
    }


}
