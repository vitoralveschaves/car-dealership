package br.com.api.dealership.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseMethodArgumentNotValidDto(HttpStatus status, List<ValidationErrorResponseDto> errors) {
}
