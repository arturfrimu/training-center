package com.arturfrimu.training.center.spring.rest.controller.driver;

import com.arturfrimu.training.center.spring.rest.entity.driver.Driver;
import com.arturfrimu.training.center.spring.rest.service.car.driver.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.findAllDrivers());
    }
}
