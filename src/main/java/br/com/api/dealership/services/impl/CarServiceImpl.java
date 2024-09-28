package br.com.api.dealership.services.impl;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.dtos.PaginationResponseDto;
import br.com.api.dealership.entities.Car;
import br.com.api.dealership.exceptions.IdNotFoundException;
import br.com.api.dealership.mappers.CarMapper;
import br.com.api.dealership.queryfilters.CarQueryFilter;
import br.com.api.dealership.repositories.CarRepository;
import br.com.api.dealership.repositories.MakeRepository;
import br.com.api.dealership.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final MakeRepository makeRepository;
    private final CarMapper carMapper;
    private final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    public CarServiceImpl(CarRepository carRepository,
                          MakeRepository makeRepository,
                          CarMapper carMapper) {
        this.carRepository = carRepository;
        this.makeRepository = makeRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarResponseDto save(CarRequestDto request) {

        if(request.makeId() != null) {
            var make = makeRepository.findById(request.makeId())
                    .orElseThrow(IdNotFoundException::new);
            var carEntity = request.toEntity(make);
            var carEntitySaved = carRepository.save(carEntity);
            logger.info("Car created: {}", carEntitySaved);
            return carMapper.entityToDto(carEntitySaved);
        }
        var carEntity = request.toEntity();
        var carEntitySaved = carRepository.save(carEntity);
        logger.info("Car created: {}", carEntitySaved);
        return carMapper.entityToDto(carEntitySaved);
    }

    @Override
    public CarResponseDto getById(UUID carId) {
        var car = carRepository.findById(carId)
                .orElseThrow(IdNotFoundException::new);
        logger.info("Car found: {}", car);
        return carMapper.entityToDto(car);
    }

    @Override
    public PaginationResponseDto getAll(CarQueryFilter filter, Integer page, Integer pageSize) {
        Page<Car> carsPage = carRepository.findAll(filter.toSpecification(), PageRequest.of(page, pageSize));
        var cars = carsPage.stream().map(carMapper::entityToDto).toList();

        logger.info("Cars listed: {}", cars);
        return new PaginationResponseDto(page, pageSize, carsPage.getTotalPages(),
                carsPage.isFirst(), carsPage.isLast(), cars);
    }

    @Override
    public CarResponseDto update(UUID carId, CarRequestDto request) {
        var carFound = carRepository.findById(carId)
                .orElseThrow(IdNotFoundException::new);
        if(request.makeId() != null) {
            var make = makeRepository.findById(request.makeId());
            make.ifPresent(carFound::setMake);
        } else {
            carFound.setMake(null);
        }
        carFound.setName(request.name());
        carFound.setYear(request.year());
        carFound.setPrice(request.price());
        carFound.setMileage(request.mileage());
        carFound.setModel(request.model());
        var updatedCar = carRepository.save(carFound);
        logger.info("Car updated: {}", updatedCar);
        return carMapper.entityToDto(updatedCar);
    }

    @Override
    public void delete(UUID carId) {
         var existCar = carRepository.existsById(carId);
         if(!existCar) {
             throw new IdNotFoundException();
         }
         carRepository.deleteById(carId);
         logger.info("Car deleted");
    }
}
