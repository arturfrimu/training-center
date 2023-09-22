package com.arturfrimu.training.center.streams.repository.customer;

import com.arturfrimu.training.center.streams.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
