package com.arturfrimu.training.center.service.customer;

import com.arturfrimu.training.center.entity.customer.Customer;
import com.arturfrimu.training.center.repository.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("db-postgress-test")
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void prepareDatabase() {
        when(customerRepository.findAll()).thenReturn(customersToSave);
    }

    @Test
    void findCustomerById() {
        Optional<Customer> customer = customerService.findById(LIONEL_MESSI.getId());

        assertThat(customer)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(
                                LIONEL_MESSI
                        )
                );
    }

    @Test
    void customerByIdNotFound() {
        Optional<Customer> customer = customerService.findById(-1L);

        assertThat(customer)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.empty()
                );
    }

    @Test
    void findCustomerByUsernameSuccess() {
        Optional<Customer> customer = customerService.findCustomerByEmailAddress(CRISTIANO_RONALDO.getEmailAddress());

        assertThat(customer)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(CRISTIANO_RONALDO)
                );
    }

    @Test
    void findCustomerByUsernameFail() {
        Optional<Customer> customer = customerService.findCustomerByEmailAddress("invalid.email@email.com");

        assertThat(customer)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.empty()
                );
    }

    @Test
    void extractCustomersFullNames() {
        List<String> customersFullNames = customerService.extractCustomersFullNames();

        assertThat(customersFullNames)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                LIONEL_MESSI.getFirstName() + " - " + LIONEL_MESSI.getLastName(),
                                CRISTIANO_RONALDO.getFirstName() + " - " + CRISTIANO_RONALDO.getLastName(),
                                NEYMAR_JR.getFirstName() + " - " + NEYMAR_JR.getLastName(),
                                ROBERT_LEWANDOWSKI.getFirstName() + " - " + ROBERT_LEWANDOWSKI.getLastName(),
                                KYLIAN_MBAPPE.getFirstName() + " - " + KYLIAN_MBAPPE.getLastName()
                        )
                );
    }

    @Test
    void makeFirstNameBeautifully() {
        String beautifullyCustomerFirstName = customerService.makeFirstNameBeautifully(LIONEL_MESSI.getId());

        assertThat(beautifullyCustomerFirstName).isEqualTo("L-I-O-N-E-L");
    }

    @Test
    void removeDashesFromPhoneNumber() {
        String phoneNumberWithoutDashes = customerService.removeDashesFromPhoneNumber(LIONEL_MESSI.getId());

        assertThat(phoneNumberWithoutDashes).isEqualTo("123456790");
    }

    @Test
    void existsEmailEndWithSuccess() {
        boolean existsEmailEndWith = customerService.existsEmailEndWith(".com");

        assertTrue(existsEmailEndWith);
    }

    @Test
    void existsEmailEndWithFail() {
        boolean existsEmailEndWith = customerService.existsEmailEndWith(".md");

        assertFalse(existsEmailEndWith);
    }

    @Test
    void isEmailValid() {
        boolean validEmail = customerService.isEmailValid("lionel.messi@email.com");

        assertTrue(validEmail);
    }

    @ParameterizedTest
    @MethodSource("emailArgumentsProvider")
    void isEmailValidFail(final String email, final String errorMessage) {
        IllegalArgumentException resourceNotFoundException = assertThrows(IllegalArgumentException.class, () -> customerService.isEmailValid(email));
        assertThat(resourceNotFoundException.getMessage()).isEqualTo(errorMessage);
    }

    private static Stream<Arguments> emailArgumentsProvider() {
        return Stream.of(
                Arguments.of("lionel.messi.email.com", "Invalid email. @ is mandatory"),
                Arguments.of("lionelmessi@email.com", "Invalid email. First part of email needs to contain ."),
                Arguments.of("lionel.messi@emailcom", "Invalid email. Second part of email needs to contain .")
        );
    }

    @Test
    void findCustomersWithTopRating5() {
        List<Customer> topRatingCustomers = customerService.findCustomersWithTopRating5();

        assertThat(topRatingCustomers)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                LIONEL_MESSI,
                                CRISTIANO_RONALDO
                        )
                );
    }

    @Test
    void splitIntoTopRatedAndBadRatedCustomers() {
        Map<Boolean, List<Customer>> ratingToCustomers = customerService.splitIntoTopRatedAndBadRatedCustomers();

        assertThat(ratingToCustomers)
                .usingRecursiveComparison()
                .isEqualTo(
                        Map.of(
                                true, List.of(LIONEL_MESSI, CRISTIANO_RONALDO),
                                false, List.of(NEYMAR_JR, ROBERT_LEWANDOWSKI, KYLIAN_MBAPPE)
                        )
                );
    }

    private static final Customer LIONEL_MESSI = new Customer(1L, "Lionel", "Messi", "lionel.messi@email.com", "123-456-790", BigDecimal.valueOf(5));
    private static final Customer CRISTIANO_RONALDO = new Customer(2L, "Cristiano", "Ronaldo", "cristiano.ronaldo@email.com", "123-456-791", BigDecimal.valueOf(5));
    private static final Customer NEYMAR_JR = new Customer(3L, "Neymar", "Jr", "neymar.jr@email.com", "123-456-792", BigDecimal.valueOf(2));
    private static final Customer ROBERT_LEWANDOWSKI = new Customer(4L, "Robert", "Lewandowski", "robert.lewandowski@email.com", "123-456-793", BigDecimal.valueOf(1));
    private static final Customer KYLIAN_MBAPPE = new Customer(5L, "Kylian", "Mbappe", "kylian.mbappe@email.com", "123-456-794", BigDecimal.valueOf(3));

    private static final List<Customer> customersToSave = List.of(
            LIONEL_MESSI,
            CRISTIANO_RONALDO,
            NEYMAR_JR,
            ROBERT_LEWANDOWSKI,
            KYLIAN_MBAPPE
    );
}