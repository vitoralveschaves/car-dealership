package br.com.api.dealership.services;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarResponseDto save(CarRequestDto request);
    CarResponseDto getById(UUID carId);
    List<CarResponseDto> getAll();
    CarResponseDto update(UUID carId, CarRequestDto request);
    void delete(UUID carId);
}
