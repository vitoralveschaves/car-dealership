package br.com.api.dealership.controllers;

import br.com.api.dealership.dtos.ResponseErrorDto;
import br.com.api.dealership.dtos.ResponseMethodArgumentNotValidDto;
import br.com.api.dealership.dtos.ValidationErrorResponseDto;
import br.com.api.dealership.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> idNotFoundHandler(IdNotFoundException exception) {
        ResponseErrorDto response = new ResponseErrorDto(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMethodArgumentNotValidDto> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMethodArgumentNotValidDto(HttpStatus.BAD_REQUEST, exceptionToList(exception)));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseErrorDto> runtimeHandler(RuntimeException exception) {
        ResponseErrorDto response = new ResponseErrorDto(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private List<ValidationErrorResponseDto> exceptionToList(MethodArgumentNotValidException exception) {
        List<ValidationErrorResponseDto> errors = new ArrayList<>();
        exception.getFieldErrors().forEach(e -> {
            errors.add(new ValidationErrorResponseDto(e.getField(), e.getDefaultMessage()));
        });
        return errors;
    }
}
