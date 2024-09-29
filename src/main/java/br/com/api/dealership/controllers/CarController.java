package br.com.api.dealership.controllers;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.dtos.PaginationResponseDto;
import br.com.api.dealership.queryfilters.CarQueryFilter;
import br.com.api.dealership.services.CarService;
import br.com.api.dealership.swagger.CarControllerSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController implements CarControllerSwagger {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponseDto> save(@RequestBody @Valid CarRequestDto request) {
        var savedCar = carService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDto> getById(@PathVariable("carId") UUID carId) {
        System.out.println(carId);
        var car = carService.getById(carId);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @GetMapping
    public ResponseEntity<PaginationResponseDto> getAll(CarQueryFilter filter,
                                                        @RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "12") Integer pageSize) {
        var cars = carService.getAll(filter, page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDto> update(@PathVariable("carId") UUID carId, @RequestBody @Valid CarRequestDto request) {
        var updatedCar = carService.update(carId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> delete(@PathVariable("carId") UUID carId) {
        carService.delete(carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
