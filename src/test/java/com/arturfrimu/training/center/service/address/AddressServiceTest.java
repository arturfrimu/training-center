package com.arturfrimu.training.center.service.address;

import com.arturfrimu.training.center.domain.address.Address;
import com.arturfrimu.training.center.repository.address.AddressRepository;
import com.arturfrimu.training.center.service.address.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("db-h2")
class AddressServiceTest {

    @MockBean
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;

    @BeforeEach
    void prepareDatabase() {
        when(addressRepository.findAll()).thenReturn(addressesToSave);
    }

    @Test
    void findAddressById() {
        var addresses = addressService.findAddressById(ION_CREANGA_10.getId());

        assertThat(addresses)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(
                                ION_CREANGA_10
                        )
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ion Creanga", "ION CREANGA", "ion creanga", "IoN cReaNGA"})
    void findAllAddressesByStreet(final String streetName) {
        var addresses = addressService.findAllAddressesByStreet(streetName);

        assertThat(addresses)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                ION_CREANGA_10,
                                ION_CREANGA_20
                        )
                );
    }

    @Test
    void findNothingByInexistentStreet() {
        var addresses = addressService.findAllAddressesByStreet("Inexistent street");
        assertThat(addresses).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ion", "ION", "ion", "IoN", "n c"})
    void findAllAddressesByStreetContaining(final String street) {
        var addresses = addressService.findAllAddressesByStreetContaining(street);

        assertThat(addresses)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                ION_CREANGA_10,
                                ION_CREANGA_20
                        )
                );
    }

    @Test
    void findNothingContainingByInexistentStreet() {
        var addresses = addressService.findAllAddressesByStreetContaining("Inexistent street");
        assertThat(addresses).isEmpty();
    }

    @Test
    void findAllAddressesByStreetCodeLessBetween() {
        var addresses = addressService.findAllAddressesByStreetNumberBetween(5, 25);

        assertThat(addresses)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                ION_CREANGA_10,
                                ION_CREANGA_20,
                                CRANGULUI_13
                        )
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ion Creanga", "ION CREANGA", "ion creanga", "IoN cReaNGA"})
    void allAddressesOnStreetHaveEvenNumbersSuccess(final String street) {
        var allAddressesOnStreetHaveEvenNumbers = addressService.allAddressesOnStreetHaveEvenNumbers(street);
        assertThat(allAddressesOnStreetHaveEvenNumbers).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tudor Vladimirescu", "TUDOR VLADIMIRESCU", "tudor vladimirescu", "TudOR VladimiRESCU"})
    void allAddressesOnStreetHaveEvenNumbersFail(final String street) {
        var allAddressesOnStreetHaveEvenNumbers = addressService.allAddressesOnStreetHaveEvenNumbers(street);
        assertThat(allAddressesOnStreetHaveEvenNumbers).isFalse();
    }

    @Test
    void noAddressWithoutPostalCode() {
        var noAddressWithoutPostalCode = addressService.noAddressWithoutPostalCode();
        assertThat(noAddressWithoutPostalCode).isTrue();
    }

    @Test
    void findAddressesByLongestStreetName() {
        var addressesByLongestStreetName = addressService.findAddressesByLongestStreetName();

        assertThat(addressesByLongestStreetName)
                .usingRecursiveComparison()
                .isEqualTo(List.of(
                        TUDOR_VLADIMIRESCU_30,
                        TUDOR_VLADIMIRESCU_35
                ));
    }

    @Test
    void findAllAddressesFromSpecificCity() {
        var allAddressesFromSpecificCity = addressService.findAllAddressesFromSpecificCity("Chisinau");

        assertThat(allAddressesFromSpecificCity)
                .usingRecursiveComparison()
                .isEqualTo(List.of(
                        ION_CREANGA_10,
                        ION_CREANGA_20,
                        TUDOR_VLADIMIRESCU_30,
                        TUDOR_VLADIMIRESCU_35
                ));
    }

    @Test
    void findAddressesWithShortestStreetName() {
        var addressesWithShortestStreetName = addressService.findAddressesWithShortestStreetName();

        assertThat(addressesWithShortestStreetName)
                .usingRecursiveComparison()
                .isEqualTo(List.of(
                        CRANGULUI_13
                ));
    }

    @Test
    void countAddressesBySpecificStreetLength() {
        var countAddressesBySpecificStreetLength = addressService.countAddressesBySpecificStreetLength();

        Map<String, Integer> countAddressToStreetLength = Map.of(
                ION_CREANGA_10.getStreet(),
                ION_CREANGA_10.getStreet().length(),
                TUDOR_VLADIMIRESCU_30.getStreet(),
                TUDOR_VLADIMIRESCU_30.getStreet().length(),
                ALBA_IULIA_77.getStreet(),
                ALBA_IULIA_77.getStreet().length(),
                CRANGULUI_13.getStreet(),
                CRANGULUI_13.getStreet().length(),
                CALEA_IESILOR_55.getStreet(),
                CALEA_IESILOR_55.getStreet().length(),
                GIACOMO_MATTEOTTI_200.getStreet(),
                GIACOMO_MATTEOTTI_200.getStreet().length()
        );

        assertThat(countAddressesBySpecificStreetLength)
                .usingRecursiveComparison()
                .isEqualTo(countAddressToStreetLength);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Italia", "ITALIA", "italia", "itaLIa"})
    void findAllCitiesInACountry(final String country) {
        var allCitiesInACountry = addressService.findAllCitiesInACountry(country);

        assertThat(allCitiesInACountry)
                .usingRecursiveComparison()
                .isEqualTo(List.of("Milano", "Sesto San Giovanni"));
    }

    @Test
    void noCitiesFoundInACountry() {
        var allCitiesInACountry = addressService.findAllCitiesInACountry("Franta");
        assertThat(allCitiesInACountry).isEmpty();
    }

    @Test
    void howManyCitiesAreInACountry() {
        var citiesInACountry = addressService.howManyCitiesAreInACountry("Italia");
        assertThat(citiesInACountry).isEqualTo(2);
    }

    @Test
    void citiesNotFoundInACountry() {
        var citiesInACountry = addressService.howManyCitiesAreInACountry("Franta");
        assertThat(citiesInACountry).isZero();
    }

    private final static Address ION_CREANGA_10 = new Address(1L, "Ion Creanga", 10, "Chisinau", "Moldova", "1234AB");
    private final static Address ION_CREANGA_20 = new Address(2L, "Ion Creanga", 20, "Chisinau", "Moldova", "1234AB");
    private final static Address TUDOR_VLADIMIRESCU_30 = new Address(3L, "Tudor Vladimirescu", 30, "Chisinau", "Moldova", "9876CD");
    private final static Address TUDOR_VLADIMIRESCU_35 = new Address(4L, "Tudor Vladimirescu", 35, "Chisinau", "Moldova", "9876CD");
    private final static Address ALBA_IULIA_77 = new Address(5L, "Alba Iulia", 77, "Nisporeni", "Moldova", "2803EF");
    private final static Address CRANGULUI_13 = new Address(6L, "Crangului", 13, "Balti", "Moldova", "3974FC");
    private final static Address CALEA_IESILOR_55 = new Address(7L, "Calea Iesilor", 55, "Milano", "Italia", "1010IT");
    private final static Address GIACOMO_MATTEOTTI_200 = new Address(8L, "Giacomo Matteotti", 200, "Sesto San Giovanni", "Italia", "20099IT");
    private final static List<Address> addressesToSave = List.of(
            ION_CREANGA_10,
            ION_CREANGA_20,
            TUDOR_VLADIMIRESCU_30,
            TUDOR_VLADIMIRESCU_35,
            ALBA_IULIA_77,
            CRANGULUI_13,
            CALEA_IESILOR_55,
            GIACOMO_MATTEOTTI_200
    );
}