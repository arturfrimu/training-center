package com.arturfrimu.training.center.spring.rest.repository.driver;

import com.arturfrimu.training.center.spring.rest.entity.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
