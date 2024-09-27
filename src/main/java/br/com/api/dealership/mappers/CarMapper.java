package br.com.api.dealership.mappers;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.entities.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car dtoToEntity(CarRequestDto request);
    CarResponseDto entityToDto(Car car);
}
