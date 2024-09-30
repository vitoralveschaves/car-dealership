package br.com.api.dealership.swagger;

import br.com.api.dealership.dtos.MakeRequestDto;
import br.com.api.dealership.dtos.MakeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Make",description = "Recurso para registrar e listar marcas de carros")
public interface MakeControllerSwagger {
    @Operation(
            summary = "Funcionalidade de registrar uma nova marca", method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Marca registrada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MakeRequestDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados nulos ou inválidos passados"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    ResponseEntity<MakeResponseDto> save(MakeRequestDto request);
    @Operation(
            summary = "Lista todas as marcas registradas",
            description = "Listagem simples de todos as marcas",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    ResponseEntity<List<MakeResponseDto>> getAll();
}
