package com.allan_dev.instrutorFacil.controller;


import com.allan_dev.instrutorFacil.dto.request.ExerciseRequest;
import com.allan_dev.instrutorFacil.dto.response.ExerciseResponse;
import com.allan_dev.instrutorFacil.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(@RequestBody @Valid ExerciseRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.createExercise(request));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> findAll(){
        return ResponseEntity.ok(exerciseService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponse> update(@PathVariable Long id, @Valid @RequestBody ExerciseRequest request){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(exerciseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
