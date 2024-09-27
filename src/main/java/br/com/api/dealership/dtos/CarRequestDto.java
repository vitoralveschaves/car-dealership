package br.com.api.dealership.dtos;

import br.com.api.dealership.entities.Car;
import br.com.api.dealership.entities.Make;
import br.com.api.dealership.enums.CarModel;

import java.math.BigDecimal;

public record CarRequestDto(String name,
                            Integer year,
                            BigDecimal price,
                            Boolean used,
                            Integer mileage,
                            CarModel model,
                            Long makeId) {

    public Car toEntity() {
        return new Car(name, year, price, used, mileage, model);
    }

    public Car toEntity(Make make) {
        return new Car(name, year, price, used, mileage, model, make);
    }
}
