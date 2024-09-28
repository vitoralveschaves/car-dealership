package br.com.api.dealership.dtos;

import br.com.api.dealership.entities.Car;
import br.com.api.dealership.entities.Make;
import br.com.api.dealership.enums.CarModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRequestDto(@NotBlank String name,
                            @NotNull Integer year,
                            @NotNull BigDecimal price,
                            @NotNull Boolean used,
                            @NotNull Integer mileage,
                            @NotNull CarModel model,
                            Long makeId) {

    public Car toEntity() {
        return new Car(name, year, price, used, mileage, model);
    }

    public Car toEntity(Make make) {
        return new Car(name, year, price, used, mileage, model, make);
    }
}

//MethodArgumentNotValidException
