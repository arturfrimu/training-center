package com.arturfrimu.training.center.spring.rest.controller.driver;

import com.arturfrimu.training.center.spring.rest.entity.driver.Driver;
import com.arturfrimu.training.center.spring.rest.service.car.driver.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.findDriverById(driverId));
    }

    @PostMapping
    public ResponseEntity<Long> addDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.addDriver(driver));
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Long> deleteDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.deleteDriver(driverId));
    }
}
