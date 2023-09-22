package com.arturfrimu.training.center.streams.service.address;

import com.arturfrimu.training.center.streams.entity.address.Address;
import com.arturfrimu.training.center.streams.repository.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> findAllAddressesByStreet(String street) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses
                .stream()
                .filter(address -> address.getStreet().equalsIgnoreCase(street))
                .toList();
    }

    public List<Address> findAllAddressesByStreetContaining(String street) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses
                .stream()
                .filter(address -> address.getStreet().toUpperCase().contains(street.toUpperCase()))
                .toList();
    }

    public List<Address> findAllAddressesByStreetNumberBetween(int firstNumber, int secondNumber) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses
                .stream()
                .filter(address -> address.getStreetNumber().compareTo(firstNumber) > 0 && address.getStreetNumber().compareTo(secondNumber) < 0)
                .toList();
    }

    public boolean allAddressesOnStreetHaveEvenNumbers(String street) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses
                .stream()
                .filter(address -> address.getStreet().toUpperCase().contains(street.toUpperCase()))
                .allMatch(address -> address.getStreetNumber() % 2 == 0);
    }

    public boolean noAddressWithoutPostalCode() {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses
                .stream()
                .allMatch(address -> Objects.nonNull(address.getPostalCode()));
    }

    // cea mai grea dintre toate
    public List<Address> findAddressesByLongestStreetName() {
        List<Address> allAddresses = addressRepository.findAll();

        Map<Integer, List<Address>> collect = allAddresses.stream().collect(groupingBy(a -> a.getStreet().length()));
        OptionalInt max = collect.keySet().stream().mapToInt(Integer::intValue).max();

        return max.isPresent() ? collect.get(max.getAsInt()) : emptyList();
    }

    public List<Address> findAllAddressesFromSpecificCity(String city) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses.stream()
                .filter(address -> address.getCity().equalsIgnoreCase(city))
                .toList();
    }

    public List<Address> findAddressesWithShortestStreetName() {
        List<Address> allAddresses = addressRepository.findAll();

        Map<Integer, List<Address>> streetNameLengthToAddresses = allAddresses.stream().collect(groupingBy(a -> a.getStreet().length()));
        OptionalInt max = streetNameLengthToAddresses.keySet().stream().mapToInt(Integer::intValue).min();

        return max.isPresent() ? streetNameLengthToAddresses.get(max.getAsInt()) : emptyList();
    }

    public Map<String, Integer> countAddressesBySpecificStreetLength() {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses.stream()
                .collect(groupingBy(Address::getStreet, collectingAndThen(toList(), addresses -> addresses.get(0).getStreet().length())));
    }

    public List<String> findAllCitiesInACountry(String country) {
        List<Address> allAddresses = addressRepository.findAll();

        Map<String, List<Address>> stateToAddresses = allAddresses.stream()
                .collect(groupingBy(address -> address.getState().toUpperCase()));

        List<Address> addresses = stateToAddresses.getOrDefault(country.toUpperCase(), emptyList());

        return addresses.stream().map(Address::getCity).toList();
    }

    public Integer howManyCitiesAreInACountry(String country) {
        List<Address> allAddresses = addressRepository.findAll();

        Map<String, List<Address>> stateToAddresses = allAddresses.stream()
                .collect(groupingBy(address -> address.getState().toUpperCase()));

        List<Address> addresses = stateToAddresses.getOrDefault(country.toUpperCase(), emptyList());

        return addresses.size();
    }

    public Optional<Address> findAddressById(Long id) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses.stream()
                .filter(address -> address.getId().equals(id))
                .findFirst();
    }

    public Optional<Address> findAddressWithBiggestStreetNumberInACity(String city) {
        List<Address> allAddresses = addressRepository.findAll();

        return allAddresses.stream()
                .filter(address -> address.getCity().equals(city))
                .max(Comparator.comparing(Address::getStreetNumber));
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
}
