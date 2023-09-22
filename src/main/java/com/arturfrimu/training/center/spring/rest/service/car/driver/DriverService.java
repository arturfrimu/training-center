package com.arturfrimu.training.center.spring.rest.service.car.driver;

import com.arturfrimu.training.center.spring.rest.entity.driver.Driver;
import com.arturfrimu.training.center.spring.rest.repository.driver.DriverRepository;
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
}
