package br.com.api.dealership.dtos;

import org.springframework.http.HttpStatus;

public record ResponseErrorDto(HttpStatus status, String message) {
}
