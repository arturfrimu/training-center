package com.arturfrimu.training.center.repository.address;

import com.arturfrimu.training.center.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
