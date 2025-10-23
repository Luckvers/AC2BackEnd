package com.example.sistemadecontroledeprojeto.Configs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.sistemadecontroledeprojeto.dtos.APIErrorDTO;
import com.example.sistemadecontroledeprojeto.dtos.RegraNegocioException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AplicationControllerAdvice {
    
    @ExceptionHandler(RegraNegocioException.class)
    public APIErrorDTO handlerRegraNegocioException(RegraNegocioException ex){
        String msg = ex.getMessage();
        return new APIErrorDTO(msg);
    }
}
