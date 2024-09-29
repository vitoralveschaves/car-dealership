package br.com.api.dealership.swagger;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.dtos.PaginationResponseDto;
import br.com.api.dealership.queryfilters.CarQueryFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Car",description = "Recurso para criar, listar, atualizar e deletar registros de carros")
public interface CarControllerSwagger {
    @Operation(
            summary = "Funcionalidade de registrar um novo carro", method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Carro registrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarRequestDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados nulos ou inválidos passados"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    ResponseEntity<CarResponseDto> save(CarRequestDto request);

    @Operation(
            summary = "Busca um carro referente ao Id informado",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca concluída"),
                    @ApiResponse(responseCode = "400", description = "Id inválido"),
                    @ApiResponse(responseCode = "404", description = "Id não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    ResponseEntity<CarResponseDto> getById(UUID carId);

    @Operation(
            summary = "Lista todos os carros",
            description = "Listagem com paginação e filtros de todos os carros",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    ResponseEntity<PaginationResponseDto> getAll(@ParameterObject CarQueryFilter filter, Integer page, Integer pageSize);

    @Operation(
            summary = "Funcionalidade de atualizar um carro", method = "PUT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Carro atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarRequestDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados nulos ou inválidos passados"),
                    @ApiResponse(responseCode = "404", description = "Id não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    ResponseEntity<CarResponseDto> update(UUID carId, CarRequestDto request);

    @Operation(summary = "Deleta um carro referente ao Id informado", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Id não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    ResponseEntity<Void> delete(UUID carId);
}
