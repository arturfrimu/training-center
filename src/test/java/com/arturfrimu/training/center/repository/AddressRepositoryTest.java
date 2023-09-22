package com.arturfrimu.training.center.repository;

import com.arturfrimu.training.center.domain.address.Address;
import com.arturfrimu.training.center.repository.address.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("db-postgress-test")
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testSaveAddress() {
        // given
        Address address = new Address();
        address.setStreet("123 Elm Street");
        address.setCity("Springfield");
        address.setState("IL");
        address.setPostalCode("12345");

        // when
        Address savedAddress = addressRepository.save(address);

        // then
        Address foundAddress = addressRepository.findById(savedAddress.getId()).get();

        assertThat(foundAddress).isNotNull();
        assertThat(address)
                .usingRecursiveComparison()
                .isEqualTo(foundAddress);
    }
}