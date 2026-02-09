package com.allan_dev.instrutorFacil.service;

import com.allan_dev.instrutorFacil.dto.request.UserRequest;
import com.allan_dev.instrutorFacil.dto.response.UserResponse;
import com.allan_dev.instrutorFacil.entity.TrainingEntity;
import com.allan_dev.instrutorFacil.entity.UserEntity;
import com.allan_dev.instrutorFacil.exceptions.ExerciseNotRegistered;
import com.allan_dev.instrutorFacil.exceptions.UserNotFound;
import com.allan_dev.instrutorFacil.exceptions.UsernameAlreadyExists;
import com.allan_dev.instrutorFacil.mapper.UserMapper;
import com.allan_dev.instrutorFacil.repository.TrainingRepository;
import com.allan_dev.instrutorFacil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;



    //============== CREATE ================
    public UserResponse createUser(UserRequest request){

        if (userRepository.existsByLogin(request.login())){
            throw new UsernameAlreadyExists();
        }

        UserEntity userSaved =  userRepository.save(UserMapper.toUserEntity(request));
        return UserMapper.toUserResponse(userSaved);
    }


    //============== LIST ALL ================
    public List<UserResponse> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList();
    }


    //============== UPDATE ================
    public UserResponse update(Long id, UserRequest userRequest) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFound::new);

        List<Long> exerciseIds = userRequest.trainingExercises();

        List<TrainingEntity> exercises =
                trainingRepository.findAllById(exerciseIds);

        //Verifica se os dados digitados conferem com os do banco de dados
        if (exercises.size() != exerciseIds.stream().distinct().count()) {
            throw new ExerciseNotRegistered();
        }

        if (userRequest.name() != null) {
            user.setName(userRequest.name());
        }

        if (userRequest.login() != null) {
            user.setLogin(userRequest.login());
        }

        if(userRequest.password() != null){
            user.setPassword(userRequest.password());
        }


        user.getTrainingExercises().clear();
        user.getTrainingExercises().addAll(exercises);


        userRepository.save(user);

        return UserMapper.toUserResponse(user);
    }



    //============== DELETE ================
    public void delete(Long id){
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(UserNotFound::new);

        userRepository.deleteById(id);
    }



}
