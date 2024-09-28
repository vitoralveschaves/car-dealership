package br.com.api.dealership.controllers;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.dtos.PaginationResponseDto;
import br.com.api.dealership.queryfilters.CarQueryFilter;
import br.com.api.dealership.services.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Car", description = "Recurso para criar, listar, atualizar e deletar registros de carros")
@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

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
    @PostMapping
    public ResponseEntity<CarResponseDto> save(@RequestBody @Valid CarRequestDto request) {
        var savedCar = carService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

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
    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDto> getById(@PathVariable("carId") UUID carId) {
        System.out.println(carId);
        var car = carService.getById(carId);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @Operation(
            summary = "Lista todos os carros",
            description = "Listagem com paginação e filtros de todos os carros",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor")
            }
    )
    @GetMapping
    public ResponseEntity<PaginationResponseDto> getAll(@ParameterObject CarQueryFilter filter,
                                                        @RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "12") Integer pageSize) {
        var cars = carService.getAll(filter, page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

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
    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDto> update(@PathVariable("carId") UUID carId, @RequestBody @Valid CarRequestDto request) {
        var updatedCar = carService.update(carId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @Operation(summary = "Deleta um carro referente ao Id informado", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Id não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> delete(@PathVariable("carId") UUID carId) {
        carService.delete(carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
