package com.arturfrimu.training.center.streams.service.customer;

import com.arturfrimu.training.center.streams.entity.customer.Customer;
import com.arturfrimu.training.center.streams.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findById(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return Optional.empty();
    }

    public Optional<Customer> findCustomerByEmailAddress(String email) {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return Optional.empty();
    }

    public List<String> extractCustomersFullNames() {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public String makeFirstNameBeautifully(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return null;
    }

    public String removeDashesFromPhoneNumber(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return null;
    }

    public boolean existsEmailEndWith(String endOfEmail) {
        List<Customer> allCustomers = customerRepository.findAll();

        return Boolean.FALSE;
    }

    public boolean isEmailValid(String email) {
        // TODO: 25.09.2023

        return Boolean.FALSE;
    }

    public List<Customer> findCustomersWithTopRating5() {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public Map<Boolean, List<Customer>> splitIntoTopRatedAndBadRatedCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return Map.of();
    }
}
