package com.allan_dev.instrutorFacil.service;


import com.allan_dev.instrutorFacil.dto.request.TrainingRequest;
import com.allan_dev.instrutorFacil.dto.response.TrainingResponse;
import com.allan_dev.instrutorFacil.entity.ExerciseEntity;
import com.allan_dev.instrutorFacil.entity.TrainingEntity;
import com.allan_dev.instrutorFacil.entity.UserEntity;
import com.allan_dev.instrutorFacil.exceptions.ExerciseAlreadyExists;
import com.allan_dev.instrutorFacil.exceptions.TrainingNotFound;
import com.allan_dev.instrutorFacil.exceptions.UserNotFound;
import com.allan_dev.instrutorFacil.mapper.TrainingMapper;
import com.allan_dev.instrutorFacil.repository.ExerciseRepository;
import com.allan_dev.instrutorFacil.repository.TrainingRepository;
import com.allan_dev.instrutorFacil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

        //==========CREATE============
    public TrainingResponse create(TrainingRequest request){
        UserEntity user = userRepository.findById(request.userId())
                .orElseThrow(UserNotFound::new);

        List<Long> exerciseIds = request.exerciseIds();
        List<ExerciseEntity> listExercises = methodFindAllById(exerciseIds);

        if (listExercises.size() != exerciseIds.size()) {
            throw new ExerciseAlreadyExists();
        }

        TrainingEntity trainingEntity = TrainingMapper.toEntity(request, user);
        trainingEntity.setExercises(listExercises);

        TrainingEntity save = trainingRepository.save(trainingEntity);

        return TrainingMapper.toResponse(save);
    }


    //========= FIND ALL (READY) ===================
    public List<TrainingResponse> findAll(){
        return trainingRepository.findAll()
                .stream()
                .map(TrainingMapper::toResponse)
                .toList();
    }


    //===============UPDATE=============
    public TrainingResponse update(Long id, TrainingRequest request){

        TrainingEntity trainingEntity = methodFindById(id);

        List<Long> exerciseIds = request.exerciseIds();
        List<ExerciseEntity> exercisesAll = methodFindAllById(exerciseIds);

        if(exerciseIds.size() != exercisesAll.size()){
            throw new ExerciseAlreadyExists();
        }

        trainingEntity.setDayOfWeek(request.dayOfWeek());
        trainingEntity.setObservations(request.observations());

        trainingEntity.getExercises().clear();
        trainingEntity.setExercises(exercisesAll);

        trainingRepository.save(trainingEntity);

        return TrainingMapper.toResponse(trainingEntity);
    }


    //===============DELETE=============
    public void delete(Long id){
        methodFindById(id);
        trainingRepository.deleteById(id);
    }


    //=======MÉTODOS AUXILIARES=======
    private TrainingEntity methodFindById (Long id){
        return trainingRepository.findById(id)
                .orElseThrow(TrainingNotFound::new);
    }

    private List<ExerciseEntity> methodFindAllById(List<Long> ids){
        return exerciseRepository.findAllById(ids);
    }
}
