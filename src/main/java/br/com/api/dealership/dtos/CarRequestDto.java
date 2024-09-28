package br.com.api.dealership.dtos;

import br.com.api.dealership.entities.Car;
import br.com.api.dealership.entities.Make;
import br.com.api.dealership.enums.CarModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRequestDto(@NotBlank @Schema(description = "Nome do carro", example = "Corolla") String name,
                            @NotNull @Schema(description = "Ano do carro", example = "2018") Integer year,
                            @NotNull @Schema(description = "Preço do carro", example = "150000") BigDecimal price,
                            @NotNull @Schema(description = "Se o carro é usado ou não") Boolean used,
                            @NotNull @Schema(description = "Quilometragem do carro", example = "10000") Integer mileage,
                            @NotNull @Schema(description = "Modelo do carro", example = "SEDAN") CarModel model,
                            @Schema(description = "Id da marca do carro, caso tenha cadastrado a marca", example = "null") Long makeId) {

    public Car toEntity() {
        return new Car(name, year, price, used, mileage, model);
    }

    public Car toEntity(Make make) {
        return new Car(name, year, price, used, mileage, model, make);
    }
}

//MethodArgumentNotValidException
