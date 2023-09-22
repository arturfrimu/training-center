package com.arturfrimu.training.center.spring.rest.repository.car;

import com.arturfrimu.training.center.spring.rest.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
