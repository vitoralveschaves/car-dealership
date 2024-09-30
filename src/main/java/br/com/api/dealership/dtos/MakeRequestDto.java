package br.com.api.dealership.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MakeRequestDto(@NotBlank @Schema(description = "Nome da marca", example = "Toyota") String name,
                             @NotBlank @Schema(description = "Documento / CNPJ da marca") String document) {
}
