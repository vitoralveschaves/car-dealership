package br.com.api.dealership.services.impl;

import br.com.api.dealership.dtos.CarRequestDto;
import br.com.api.dealership.dtos.CarResponseDto;
import br.com.api.dealership.entities.Car;
import br.com.api.dealership.entities.Make;
import br.com.api.dealership.enums.CarModel;
import br.com.api.dealership.exceptions.IdNotFoundException;
import br.com.api.dealership.mappers.CarMapper;
import br.com.api.dealership.repositories.CarRepository;
import br.com.api.dealership.repositories.MakeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private MakeRepository makeRepository;
    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    private ArgumentCaptor<Car> carArgumentCaptor;
    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @Nested
    class Save {
        @Test
        @DisplayName("Should save a car with success when make is null")
        @MockitoSettings(strictness = Strictness.LENIENT)
        void saveACarWithSuccessWhenMakeIsNull() {

            var id = UUID.randomUUID();

            var car = new Car(
                    id,"Corolla",2018, BigDecimal.valueOf(100000),
                    true,3000,CarModel.SEDAN, null
            );

            var carRequest = new CarRequestDto(
                    "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, null
            );

            var carResponse = new CarResponseDto(
                    id, "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, null
            );

            doReturn(car).when(carRepository).save(carArgumentCaptor.capture());
            doReturn(carResponse).when(carMapper).entityToDto(carArgumentCaptor.capture());

            var response = carService.save(carRequest);

            assertNotNull(response);
            assertEquals(response.name(), carArgumentCaptor.getAllValues().getFirst().getName());
            assertEquals(response.price(), carArgumentCaptor.getAllValues().getFirst().getPrice());
            assertEquals(response.model(), carArgumentCaptor.getAllValues().getFirst().getModel());

            verify(carRepository, times(1)).save(carArgumentCaptor.getAllValues().get(0));
            verify(carMapper, times(1)).entityToDto(carArgumentCaptor.getAllValues().get(1));
        }

        @Test
        @DisplayName("Should save a car with success when make exists")
        @MockitoSettings(strictness = Strictness.LENIENT)
        void saveACarWithSuccessWhenMakeExists() {
            var make = new Make(1L, "Toyota", "1234");
            var id = UUID.randomUUID();

            var car = new Car(
                    id,"Corolla",2018, BigDecimal.valueOf(100000),
                    true,3000,CarModel.SEDAN, make
            );

            var carRequest = new CarRequestDto(
                    "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, make.getId()
            );

            var carResponse = new CarResponseDto(
                    id, "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, make
            );

            doReturn(Optional.of(make)).when(makeRepository).findById(longArgumentCaptor.capture());
            doReturn(car).when(carRepository).save(carArgumentCaptor.capture());
            doReturn(carResponse).when(carMapper).entityToDto(carArgumentCaptor.capture());

            var response = carService.save(carRequest);

            assertNotNull(response);
            assertEquals(response.name(), carArgumentCaptor.getAllValues().getFirst().getName());
            assertEquals(response.price(), carArgumentCaptor.getAllValues().getFirst().getPrice());
            assertEquals(response.model(), carArgumentCaptor.getAllValues().getFirst().getModel());
            assertEquals(response.make(), carArgumentCaptor.getAllValues().getFirst().getMake());
            assertEquals(response.make().getId(), longArgumentCaptor.getValue());

            verify(makeRepository, times(1)).findById(longArgumentCaptor.getValue());
            verify(carRepository, times(1)).save(carArgumentCaptor.getAllValues().get(0));
            verify(carMapper, times(1)).entityToDto(carArgumentCaptor.getAllValues().get(1));
        }

        @Test
        @DisplayName("Should not save a car when make id is different")
        void notSaveACarWhenMakeIdIsDifferent() {
            var make = new Make(1L, "Toyota", "1234");

            var carRequest = new CarRequestDto(
                    "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, make.getId()
            );

            IdNotFoundException exception = Assertions.assertThrows(IdNotFoundException.class, () -> carService.save(carRequest));
            assertTrue(exception.getMessage().contains("Id not found"));

            verify(makeRepository, times(1)).findById(any());
        }
    }

    @Nested
    class GetById {
        @Test
        @DisplayName("Should get car by id with success when optional is present")
        void getByIdWithSuccess() {

            var id = UUID.randomUUID();
            var make = new Make(1L, "Toyota", "1234");

            var car = new Car(
                    id,"Corolla",2018, BigDecimal.valueOf(100000),
                    true,3000,CarModel.SEDAN,make
            );

            var carResponse = new CarResponseDto(
                    id, "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, make
            );

            doReturn(Optional.of(car)).when(carRepository).findById(uuidArgumentCaptor.capture());
            doReturn(carResponse).when(carMapper).entityToDto(car);

            CarResponseDto cars = carService.getById(car.getId());
            assertEquals(cars.id(), uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should not get car by id when optional is false")
        void notGetByIdWhenUserOptionalIsFalse() {
            var id = UUID.randomUUID();
            IdNotFoundException exception = Assertions.assertThrows(IdNotFoundException.class, () -> carService.getById(id));
            assertTrue(exception.getMessage().contains("Id not found"));

            verify(carRepository, times(1)).findById(any());
        }
    }

    @Nested
    class Update {
        @Test
        @DisplayName("Should update a car with success when make is null")
        @MockitoSettings(strictness = Strictness.LENIENT)
        void updateACarWithSuccessWhenMakeIsNull() {
            var id = UUID.randomUUID();

            var car = new Car(
                    id,"Corolla",2018, BigDecimal.valueOf(100000),
                    true,3000,CarModel.SEDAN, null
            );

            var carRequest = new CarRequestDto(
                    "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, null
            );

            var carResponse = new CarResponseDto(
                    id, "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, null
            );

            doReturn(Optional.of(car)).when(carRepository).findById(uuidArgumentCaptor.capture());
            doReturn(car).when(carRepository).save(carArgumentCaptor.capture());
            doReturn(carResponse).when(carMapper).entityToDto(carArgumentCaptor.capture());

            var res = carService.update(id, carRequest);

            assertEquals(res.id(), uuidArgumentCaptor.getValue());
            assertEquals(res.name(), carArgumentCaptor.getAllValues().getFirst().getName());
            assertEquals(res.price(), carArgumentCaptor.getAllValues().getFirst().getPrice());
            assertEquals(res.model(), carArgumentCaptor.getAllValues().getFirst().getModel());
            assertEquals(res.make(), carArgumentCaptor.getAllValues().getFirst().getMake());

            verify(carRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(carRepository, times(1)).save(carArgumentCaptor.getAllValues().getFirst());
        }

        @Test
        @DisplayName("Should update a car with success when make exists")
        @MockitoSettings(strictness = Strictness.LENIENT)
        void updateACarWithSuccessWhenMakeExists() {
            var id = UUID.randomUUID();
            var make = new Make(1L, "Toyota", "1234");

            var car = new Car(
                    id,"Corolla",2018, BigDecimal.valueOf(100000),
                    true,3000,CarModel.SEDAN,make
            );

            var carRequest = new CarRequestDto(
                    "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, make.getId()
            );

            var carResponse = new CarResponseDto(
                    id, "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, make
            );

            doReturn(Optional.of(car)).when(carRepository).findById(uuidArgumentCaptor.capture());
            doReturn(car).when(carRepository).save(carArgumentCaptor.capture());
            doReturn(carResponse).when(carMapper).entityToDto(carArgumentCaptor.capture());

            carService.update(id, carRequest);

            verify(makeRepository, times(1)).findById(any());
            verify(carRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(carRepository, times(1)).save(carArgumentCaptor.getAllValues().getFirst());
        }

        @Test
        @DisplayName("Should not update a car when car id not exists")
        void notUpdateACarWhenCarIdNotExists() {
            var id = UUID.randomUUID();

            var carRequest = new CarRequestDto(
                    "Corolla", 2018,BigDecimal.valueOf(100000),
                    true, 3000, CarModel.SEDAN, null
            );

            IdNotFoundException exception = Assertions.assertThrows(IdNotFoundException.class, () -> carService.update(id, carRequest));
            assertTrue(exception.getMessage().contains("Id not found"));
            verify(carRepository, times(1)).findById(any());
        }
    }

    @Nested
    class Delete {
        @Test
        @DisplayName("Should delete a car with success when car exists")
        void deleteACarWithSuccess() {

            var carId = UUID.randomUUID();
            doReturn(true).when(carRepository).existsById(uuidArgumentCaptor.capture());
            doNothing().when(carRepository).deleteById(uuidArgumentCaptor.capture());

            carService.delete(carId);

            assertEquals(carId, uuidArgumentCaptor.getAllValues().get(0));
            assertEquals(carId, uuidArgumentCaptor.getAllValues().get(1));

            verify(carRepository, times(1)).existsById(uuidArgumentCaptor.getAllValues().get(0));
            verify(carRepository, times(1)).deleteById(uuidArgumentCaptor.getAllValues().get(1));
        }

        @Test
        @DisplayName("Should not delete a car when car not exists")
        void notDeleteACarWithSuccess() throws IdNotFoundException{

            var carId = UUID.randomUUID();
            doReturn(false).when(carRepository).existsById(uuidArgumentCaptor.capture());

            Assertions.assertThrows(IdNotFoundException.class, () -> carService.delete(carId));

            verify(carRepository, times(1)).existsById(any());
            verify(carRepository, times(0)).deleteById(any());
        }
    }
}