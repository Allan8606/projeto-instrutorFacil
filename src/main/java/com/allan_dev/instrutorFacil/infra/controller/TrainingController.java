package com.allan_dev.instrutorFacil.infra.controller;


import com.allan_dev.instrutorFacil.infra.dto.request.TrainingRequest;
import com.allan_dev.instrutorFacil.infra.dto.response.TrainingResponse;
import com.allan_dev.instrutorFacil.domain.service.TrainingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService service;

    @PostMapping
    public ResponseEntity<TrainingResponse> create(@RequestBody @Valid TrainingRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TrainingResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponse> update(@PathVariable Long id, @RequestBody @Valid TrainingRequest request){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
