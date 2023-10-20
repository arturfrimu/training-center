package com.arturfrimu.training.center.streams.service.customer;

import com.arturfrimu.training.center.streams.entity.customer.Customer;
import com.arturfrimu.training.center.streams.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Collections.emptyList;

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
        return allCustomers.stream().filter(customer -> customer.getEmailAddress().equals(email))
                .findFirst();
    }

    public List<String> extractCustomersFullNames() {
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers.stream()
                .map(customer -> customer.getFirstName().concat(" - ").concat(customer.getLastName()))
                .collect(Collectors.toList());
    }

    public String makeFirstNameBeautifully(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        String firstName = customer.getFirstName();
        char[] chars = firstName.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
            if (i < chars.length - 1) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
        // method 2
//       return firstName.chars()
//                .mapToObj(c -> String.valueOf((char) c))
//                .collect(Collectors.joining("-"));
    }

        public String removeDashesFromPhoneNumber(Long id) {
        List<Customer> allCustomers = customerRepository.findAll();

        // TODO: 25.09.2023

        return null;
    }

    public boolean existsEmailEndWith(String endOfEmail) {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream().anyMatch((c) -> c.getEmailAddress().endsWith(endOfEmail));
    }

        public boolean isEmailValid (String email){
            // TODO: 25.09.2023

            return FALSE;
        }

        public List<Customer> findCustomersWithTopRating5 () {
            List<Customer> allCustomers = customerRepository.findAll();

            // TODO: 25.09.2023

            return emptyList();
        }

        public Map<Boolean, List<Customer>> splitIntoTopRatedAndBadRatedCustomers () {
            List<Customer> allCustomers = customerRepository.findAll();

            // TODO: 25.09.2023

            return Map.of();
        }
    }
