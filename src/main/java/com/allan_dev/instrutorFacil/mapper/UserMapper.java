package com.allan_dev.instrutorFacil.mapper;

import com.allan_dev.instrutorFacil.dto.request.UserRequest;
import com.allan_dev.instrutorFacil.dto.response.UserResponse;
import com.allan_dev.instrutorFacil.entity.TrainingEntity;
import com.allan_dev.instrutorFacil.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserMapper {

    public static UserEntity toUserEntity(UserRequest request){

        List<TrainingEntity> list = request.trainingExercises()
                .stream()
                .map(trainingId -> TrainingEntity
                        .builder()
                        .id(trainingId)
                        .build())
                .toList();

        return UserEntity
                .builder()
                .name(request.name())
                .login(request.login())
                .password(request.password())
                .trainingExercises(list)
                .build();
    }


    public static UserResponse toUserResponse(UserEntity entity){

        List<String> list = entity.getTrainingExercises()
                .stream()
                .map(training -> training.getDayOfWeek().name() + " - " +
                        training.getExercises().stream().map(name -> name.getName())
                                .toList()
                        )
                .toList();



        return UserResponse
                .builder()
                .id(entity.getUserId())
                .name(entity.getName())
                .login(entity.getLogin())
                .trainingExercises(list)
                .build();
    }

}
