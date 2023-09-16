package com.arturfrimu.training.center.repository.customer;

import com.arturfrimu.training.center.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
