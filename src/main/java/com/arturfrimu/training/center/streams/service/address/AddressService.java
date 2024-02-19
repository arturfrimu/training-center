package com.arturfrimu.training.center.streams.service.address;

import com.arturfrimu.training.center.streams.entity.address.Address;
import com.arturfrimu.training.center.streams.repository.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> findAllAddressesByStreet(String street) {
        List<Address> allAddresses = addressRepository.findAll();
        return allAddresses.stream()
                .filter(address -> address.getStreet().toLowerCase().equals(street.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Address> findAllAddressesByStreetContaining(String street) {
        List<Address> allAddresses = addressRepository.findAll();
        return allAddresses.stream()
                .filter(address -> address.getStreet().toLowerCase().contains(street.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Address> findAllAddressesByStreetNumberBetween(int firstNumber, int secondNumber) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public boolean allAddressesOnStreetHaveEvenNumbers(String street) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return Boolean.FALSE;
    }

    public boolean noAddressWithoutPostalCode() {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return Boolean.FALSE;
    }

    // cea mai grea dintre toate
    public List<Address> findAddressesByLongestStreetName() {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public List<Address> findAllAddressesFromSpecificCity(String city) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public List<Address> findAddressesWithShortestStreetName() {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public Map<String, Integer> countAddressesBySpecificStreetLength() {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return Map.of();
    }

    public List<String> findAllCitiesInACountry(String country) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public Integer howManyCitiesAreInACountry(String country) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return null;
    }

    public Optional<Address> findAddressById(Long id) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return empty();
    }

    public Optional<Address> findAddressWithBiggestStreetNumberInACity(String city) {
        List<Address> allAddresses = addressRepository.findAll();

        // TODO: 25.09.2023

        return empty();
    }
}
