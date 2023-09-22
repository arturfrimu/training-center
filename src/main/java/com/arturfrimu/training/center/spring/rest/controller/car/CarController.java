package com.arturfrimu.training.center.spring.rest.controller.car;

import com.arturfrimu.training.center.spring.rest.entity.car.Car;
import com.arturfrimu.training.center.spring.rest.service.car.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Long> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.addCar(car).getId());
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.findAllCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.findCarById(carId));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Long> deleteCar(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.deleteCar(carId));
    }
}
