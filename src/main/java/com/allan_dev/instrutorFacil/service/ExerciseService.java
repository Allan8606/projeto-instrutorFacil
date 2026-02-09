package com.allan_dev.instrutorFacil.service;


import com.allan_dev.instrutorFacil.dto.request.ExerciseRequest;
import com.allan_dev.instrutorFacil.dto.response.ExerciseResponse;
import com.allan_dev.instrutorFacil.entity.ExerciseEntity;
import com.allan_dev.instrutorFacil.entity.TrainingEntity;
import com.allan_dev.instrutorFacil.exceptions.ExerciseAlreadyExists;
import com.allan_dev.instrutorFacil.exceptions.ExerciseNotFound;
import com.allan_dev.instrutorFacil.mapper.ExerciseMapper;
import com.allan_dev.instrutorFacil.repository.ExerciseRepository;
import com.allan_dev.instrutorFacil.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;

    //==========CREATE============
    public ExerciseResponse createExercise(ExerciseRequest request){
        if (exerciseRepository.existsByNameIgnoreCase(request.name())) {
            throw new ExerciseAlreadyExists();
        };

        ExerciseEntity exerciseEntity = ExerciseMapper.toExerciseEntity(request);
        ExerciseEntity savedExercise = exerciseRepository.save(exerciseEntity);

        return ExerciseMapper.toExerciseResponse(savedExercise);
    }

    //==========FIND ALL (READY)============
    public List<ExerciseResponse> findAll(){
        return exerciseRepository.findAll()
                .stream()
                .map(ExerciseMapper::toExerciseResponse)
                .toList();
    }

    //==========UPDATE============
    public ExerciseResponse update(Long id, ExerciseRequest request){
        ExerciseEntity exercise = exerciseRepository.findById(id)
                .orElseThrow(ExerciseNotFound::new);

        if(request.name() != null){
            exercise.setName(request.name());
        }

        if(request.description() != null){
            exercise.setDescription(request.description());
        }

        if(request.link() != null){
            exercise.setLink(request.link());
        }


        List<Long> idsRequest = request.trainingExercises();

        List<TrainingEntity> exercisesByIds = trainingRepository.findAllById(request.trainingExercises());

        if (exercisesByIds.size() != idsRequest.size()){
            throw new ExerciseAlreadyExists();
        }

        exercise.getTrainingExercises().clear();
        exercise.setTrainingExercises(exercisesByIds);

        exerciseRepository.save(exercise);

        return ExerciseMapper.toExerciseResponse(exercise);
    }

    //==========DELETE============
    public void delete(Long id){
        ExerciseEntity exerciseEntity = exerciseRepository.findById(id)
                .orElseThrow(ExerciseNotFound::new);

        exerciseRepository.deleteById(id);
    }
}
