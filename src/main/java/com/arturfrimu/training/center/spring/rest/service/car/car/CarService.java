package com.arturfrimu.training.center.spring.rest.service.car.car;

import com.arturfrimu.training.center.spring.rest.entity.car.Car;
import com.arturfrimu.training.center.spring.rest.repository.car.CarRepository;
import com.arturfrimu.training.center.streams.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car findCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id %s".formatted(carId)));
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Long deleteCar(Long carId) {
        Car carInDb = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id %s".formatted(carId)));
        carRepository.delete(carInDb);
        return carId;
    }
}
