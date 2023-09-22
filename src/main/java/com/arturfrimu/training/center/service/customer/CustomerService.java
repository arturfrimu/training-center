package com.arturfrimu.training.center.service.customer;

import com.arturfrimu.training.center.entity.customer.Customer;
import com.arturfrimu.training.center.exception.ResourceNotFoundException;
import com.arturfrimu.training.center.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findById(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    public Optional<Customer> findCustomerByEmailAddress(String email) {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream()
                .filter(customer -> customer.getEmailAddress().equals(email))
                .findFirst();
    }

    public List<String> extractCustomersFullNames() {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream()
                .map(customer -> customer.getFirstName() + " - " + customer.getLastName())
                .toList();
    }

    public String makeFirstNameBeautifully(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        Customer customer = allCustomers.stream()
                .filter(currentCustomer -> currentCustomer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: %s".formatted(id)));

        String[] splitCustomerFirstName = customer.getFirstName().toUpperCase().split("");

        return String.join("-", splitCustomerFirstName);
    }

    public String removeDashesFromPhoneNumber(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        Customer customer = allCustomers.stream()
                .filter(currentCustomer -> currentCustomer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: %s".formatted(id)));

        return customer.getPhoneNumber().replace("-", "");
    }

    public boolean existsEmailEndWith(String endOfEmail) {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream().anyMatch(customer -> customer.getEmailAddress().endsWith(endOfEmail));
    }

    public boolean isEmailValid(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email. @ is mandatory");
        }
        String[] splitEmail = email.split("@");
        if (!splitEmail[0].contains(".")) {
            throw new IllegalArgumentException("Invalid email. First part of email needs to contain .");
        }
        if (!splitEmail[1].contains(".")) {
            throw new IllegalArgumentException("Invalid email. Second part of email needs to contain .");
        }

        return true;
    }

    public List<Customer> findCustomersWithTopRating5() {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream()
                .filter(customer -> customer.getRating().equals(BigDecimal.valueOf(5)))
                .toList();
    }

    public Map<Boolean, List<Customer>> splitIntoTopRatedAndBadRatedCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream().collect(Collectors.partitioningBy(customer -> customer.getRating().compareTo(BigDecimal.valueOf(3)) > 0));
    }
}
