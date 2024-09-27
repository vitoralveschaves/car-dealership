package br.com.api.dealership.controllers;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.queryfilters.CarQueryFilter;
import br.com.api.dealership.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarResponseDto> save(@RequestBody CarRequestDto request) {
        var savedCar = carService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDto> getById(@PathVariable("carId") UUID carId) {
        var car = carService.getById(carId);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDto>> getAll(CarQueryFilter filter) {
        var cars = carService.getAll(filter);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDto> update(@PathVariable("carId") UUID carId, @RequestBody CarRequestDto request) {
        var updatedCar = carService.update(carId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> delete(@PathVariable("carId") UUID carId) {
        carService.delete(carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
