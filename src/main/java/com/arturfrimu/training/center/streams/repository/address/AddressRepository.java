package com.arturfrimu.training.center.streams.repository.address;

import com.arturfrimu.training.center.streams.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
