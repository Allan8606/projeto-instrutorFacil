package com.allan_dev.instrutorFacil.handler;


import com.allan_dev.instrutorFacil.exceptions.*;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ProblemDetail> createResponse(HttpStatus status, String title, String detail){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        return ResponseEntity
                .status(status)
                .body(problemDetail);
    }


    @ExceptionHandler(ExerciseAlreadyExists.class)
    public ResponseEntity<ProblemDetail> exerciseAlreadyExists(ExerciseAlreadyExists error){

        return createResponse(HttpStatus.CONFLICT,
                "Exercício já cadastrado.",
                error.getMessage());

        /*Pode ser feito assim
        ProblemDetail problemDetail = ProblemDetail.forStatus(
                HttpStatus.CONFLICT
        );

        problemDetail.setDetail("Você tentou criar um exercício que já está cadstrado no seu banco de dados");
        problemDetail.setTitle(error.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT).body(problemDetail);

         */
    }


    @ExceptionHandler(ExerciseNotFound.class)
    public ResponseEntity<ProblemDetail> exerciseFound(ExerciseNotFound error){

        return createResponse(HttpStatus.NOT_FOUND,
                error.getMessage(),
                error.getMessage());
    }


    @ExceptionHandler(ExerciseNotRegistered.class)
    public ResponseEntity<ProblemDetail> exerciseNotRegistred(ExerciseNotRegistered error){
        return createResponse(HttpStatus.BAD_REQUEST,
                "Exercício não registrado.",
                error.getMessage());
    }

    @ExceptionHandler(TrainingNotFound.class)
    public ResponseEntity<ProblemDetail> trainingNotFound (TrainingNotFound error){
        return createResponse(HttpStatus.NOT_FOUND,
                error.getMessage(),
                error.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<ProblemDetail> usernameAlreadyExists (UsernameAlreadyExists error){
        return createResponse(HttpStatus.CONFLICT,
                "Usuário já cadastrado.",
                error.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ProblemDetail> userNotFound (UserNotFound error){
        return createResponse(HttpStatus.CONFLICT,
                error.getMessage(),
                error.getMessage());
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        // 1. O Recipiente do Erro
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                status,
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."
        );
        problemDetail.setTitle("Erro de Validação de Campos");

        // 2. A Coleta dos Erros
        Map<String, String> invalidFields = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            invalidFields.put(fieldName, errorMessage);
        });

        // 3. A Personalização da Resposta
        problemDetail.setProperty("fields", invalidFields);

        return ResponseEntity.status(status).body(problemDetail);
    }





}
