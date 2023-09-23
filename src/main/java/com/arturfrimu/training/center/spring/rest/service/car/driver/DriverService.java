package com.arturfrimu.training.center.spring.rest.service.car.driver;

import com.arturfrimu.training.center.spring.rest.entity.driver.Driver;
import com.arturfrimu.training.center.spring.rest.repository.driver.DriverRepository;
import com.arturfrimu.training.center.streams.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver findDriverById(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id %s".formatted(driverId)));
    }

    public Long addDriver(Driver driver) {
        return driverRepository.save(driver).getId();
    }

    public Long deleteDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id %s".formatted(driverId)));

        driverRepository.delete(driver);

        return driverId;
    }
}
