package com.arturfrimu.training.center.service.order;

import com.arturfrimu.training.center.domain.address.Address;
import com.arturfrimu.training.center.domain.customer.Customer;
import com.arturfrimu.training.center.domain.order.Order;
import com.arturfrimu.training.center.domain.product.Product;
import com.arturfrimu.training.center.exception.ResourceNotFoundException;
import com.arturfrimu.training.center.repository.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("db-postgress-test")
class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @BeforeEach
    void prepareDatabase() {
        when(orderRepository.findAll()).thenReturn(ordersToSave);
    }

    @Test
    void findOrderById() {
        Optional<Order> orderById = orderService.findOrderById(FIRST_ORDER_LIONEL_MESSI.getId());

        assertThat(orderById)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(FIRST_ORDER_LIONEL_MESSI)
                );
    }


    @Test
    void orderByIdNotFound() {
        Optional<Order> orderById = orderService.findOrderById(-1L);

        assertThat(orderById)
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.empty()
                );
    }

    @Test
    void findAllOrdersDidByCustomer() {
        List<Order> ordersDidByCustomer = orderService.findAllOrdersDidByCustomer(FIRST_ORDER_LIONEL_MESSI.getCustomer().getId());

        assertThat(ordersDidByCustomer)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                FIRST_ORDER_LIONEL_MESSI,
                                SECOND_ORDER_LIONEL_MESSI
                        )
                );
    }

    @Test
    void getTotalAmountOfOrder() {
        BigDecimal totalAmountOfOrder = orderService.calculateTotalAmountOfOrder(FIRST_ORDER_LIONEL_MESSI.getId());

        assertThat(totalAmountOfOrder).isEqualTo(BigDecimal.valueOf(1650));
    }

    @Test
    void getTotalAmountOfOrderFail() {
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> orderService.calculateTotalAmountOfOrder(-1L));
        assertThat(resourceNotFoundException.getMessage()).isEqualTo("Order not found with id: %s".formatted(-1L));
    }

    @Test
    void groupOrdersByOrderStatus() {
        Map<Order.OrderStatus, List<Order>> orderStatusToOrders = orderService.groupOrdersByOrderStatus();

        assertThat(orderStatusToOrders)
                .usingRecursiveComparison()
                .isEqualTo(
                        Map.of(
                                Order.OrderStatus.PENDING, List.of(SECOND_ORDER, SIXTH_ORDER),
                                Order.OrderStatus.PROCESSING, List.of(FIRST_ORDER_LIONEL_MESSI, SECOND_ORDER_LIONEL_MESSI, FIFTH_ORDER, NINTH_ORDER, TENTH_ORDER),
                                Order.OrderStatus.SHIPPED, List.of(THIRD_ORDER, SEVENTH_ORDER),
                                Order.OrderStatus.DELIVERED, List.of(FOURTH_ORDER, EIGHTH_ORDER),
                                Order.OrderStatus.CANCELED, emptyList()
                        )
                );
    }

    @Test
    void findAllOrdersThatHaveTotalAmountGreaterThan() {
        Map<Boolean, List<Order>> ordersToTotalAmount = orderService.findAllOrdersThatHaveTotalAmountGreaterThan(BigDecimal.valueOf(2000));

        List<Order> ordersWithTotalAmountGreaterThan2000 = ordersToTotalAmount.get(true);

        assertThat(ordersWithTotalAmountGreaterThan2000)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                SIXTH_ORDER,
                                SEVENTH_ORDER
                        )
                );

        List<Order> ordersWithTotalAmountLessOrEqualsThan2000 = ordersToTotalAmount.get(false);

        assertThat(ordersWithTotalAmountLessOrEqualsThan2000)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                FIRST_ORDER_LIONEL_MESSI,
                                SECOND_ORDER_LIONEL_MESSI,
                                SECOND_ORDER,
                                THIRD_ORDER,
                                FOURTH_ORDER,
                                FIFTH_ORDER,
                                EIGHTH_ORDER,
                                NINTH_ORDER,
                                TENTH_ORDER
                        )
                );
    }

    private static final Order FIRST_ORDER_LIONEL_MESSI = new Order(
            1L,
            new Customer(1L, "Lionel", "Messi", "lionel.messi@email.com", "123-456-790", BigDecimal.valueOf(5), new Address(1L, "Ion Creanga", 10, "Rosario", "Argentina", "1234AB")),
            new Address(1L, "Ion Creanga", 10, "Rosario", "Argentina", "1234AB"),
            Order.OrderStatus.PROCESSING,
            Order.PaymentMethod.CASH_ON_DELIVERY,
            LocalDateTime.now(),
            List.of(
                    new Product(1L, "Adidas Messi Football Boots", "Signature football boots", BigDecimal.valueOf(150), 1),
                    new Product(51L, "IPhone 14 PRO", "Good choice", BigDecimal.valueOf(1500), 100)
            )
    );

    private static final Order SECOND_ORDER_LIONEL_MESSI = new Order(
            1L,
            new Customer(1L, "Lionel", "Messi", "lionel.messi@email.com", "123-456-790", BigDecimal.valueOf(5), new Address()),
            new Address(1L, "Ion Creanga", 10, "Rosario", "Argentina", "1234AB"),
            Order.OrderStatus.PROCESSING,
            Order.PaymentMethod.CASH_ON_DELIVERY,
            LocalDateTime.now(),
            List.of(
                    new Product(52L, "Adidas Messi Football Boots new collection", "Signature football boots new collection", BigDecimal.valueOf(300), 20)
            )
    );

    private static final Order SECOND_ORDER = new Order(
            2L,
            new Customer(2L, "Cristiano", "Ronaldo", "cristiano.ronaldo@email.com", "987-654-321", BigDecimal.valueOf(8)),
            new Address(2L, "Stefan cel Mare", 15, "Santos Aveiro", "Portugalia", "5678CD"),
            Order.OrderStatus.PENDING,
            Order.PaymentMethod.CREDIT_CARD,
            LocalDateTime.now(),
            List.of(
                    new Product(2L, "CR7 Soccer Ball", "Signature soccer ball", BigDecimal.valueOf(50), 2),
                    new Product(3L, "Nike CR7 Football Cleats", "Signature football cleats", BigDecimal.valueOf(200), 1)
            )
    );

    private static final Order THIRD_ORDER = new Order(
            3L,
            new Customer(3L, "Neymar", "Jr.", "neymar.jr@email.com", "111-222-333", BigDecimal.valueOf(6)),
            new Address(3L, "Stefan cel Mare", 20, "Sao Paulo", "Brazilia", "9012EF"),
            Order.OrderStatus.SHIPPED,
            Order.PaymentMethod.PAYPAL,
            LocalDateTime.now(),
            List.of(
                    new Product(4L, "Nike Neymar Football Jersey", "Signature football jersey", BigDecimal.valueOf(80), 1)
            )
    );

    private static final Order FOURTH_ORDER = new Order(
            4L,
            new Customer(4L, "Sergio", "Ramos", "sergio.ramos@email.com", "777-888-999", BigDecimal.valueOf(7)),
            new Address(4L, "Stefan cel Mare", 25, "Madrid", "Spania", "3456GH"),
            Order.OrderStatus.DELIVERED,
            Order.PaymentMethod.CASH_ON_DELIVERY,
            LocalDateTime.now(),
            List.of(
                    new Product(5L, "Real Madrid Home Kit", "Official football kit", BigDecimal.valueOf(100), 1)
            )
    );

    private static final Order FIFTH_ORDER = new Order(
            5L,
            new Customer(5L, "Kylian", "Mbappé", "kylian.mbappe@email.com", "555-666-777", BigDecimal.valueOf(9)),
            new Address(5L, "Stefan cel Mare", 30, "Paris", "Franta", "7890IJ"),
            Order.OrderStatus.PROCESSING,
            Order.PaymentMethod.PAYPAL,
            LocalDateTime.now(),
            List.of(
                    new Product(6L, "PSG Football Scarf", "Official club scarf", BigDecimal.valueOf(20), 3)
            )
    );

    private static final Order SIXTH_ORDER = new Order(
            6L,
            new Customer(6L, "Angelina", "Jolie", "angelina.jolie@email.com", "111-222-333", BigDecimal.valueOf(10)),
            new Address(6L, "Hollywood Blvd", 123, "Los Angeles", "United States", "90210"),
            Order.OrderStatus.PENDING,
            Order.PaymentMethod.CREDIT_CARD,
            LocalDateTime.now(),
            List.of(
                    new Product(7L, "Canon EOS 5D Mark IV", "Professional DSLR camera", BigDecimal.valueOf(2500), 5)
            )
    );

    private static final Order SEVENTH_ORDER = new Order(
            7L,
            new Customer(7L, "Elon", "Musk", "elon.musk@email.com", "444-555-666", BigDecimal.valueOf(11)),
            new Address(7L, "SpaceX Ave", 1, "Los Angeles", "United States", "90211"),
            Order.OrderStatus.SHIPPED,
            Order.PaymentMethod.PAYPAL,
            LocalDateTime.now(),
            List.of(
                    new Product(8L, "Tesla Model 3", "Electric car for the future", BigDecimal.valueOf(45000), 3)
            )
    );

    private static final Order EIGHTH_ORDER = new Order(
            8L,
            new Customer(8L, "Oprah", "Winfrey", "oprah.winfrey@email.com", "777-888-999", BigDecimal.valueOf(12)),
            new Address(8L, "Oprah's Estate", 5, "Santa Barbara", "United States", "90212"),
            Order.OrderStatus.DELIVERED,
            Order.PaymentMethod.CASH_ON_DELIVERY,
            LocalDateTime.now(),
            List.of(
                    new Product(9L, "Amazon Kindle Paperwhite", "High-resolution e-reader", BigDecimal.valueOf(150), 20)
            )
    );

    private static final Order NINTH_ORDER = new Order(
            9L,
            new Customer(9L, "Bill", "Gates", "bill.gates@email.com", "123-456-789", BigDecimal.valueOf(13)),
            new Address(9L, "Microsoft Campus", 1, "Redmond", "United States", "90213"),
            Order.OrderStatus.PROCESSING,
            Order.PaymentMethod.CREDIT_CARD,
            LocalDateTime.now(),
            List.of(
                    new Product(10L, "Microsoft Surface Pro 7", "Versatile 2-in-1 laptop", BigDecimal.valueOf(1200), 15)
            )
    );

    private static final Order TENTH_ORDER = new Order(
            10L,
            new Customer(10L, "Elvis", "Presley", "elvis.presley@email.com", "555-666-777", BigDecimal.valueOf(14)),
            new Address(10L, "Graceland", 3764, "Memphis", "United States", "90214"),
            Order.OrderStatus.PROCESSING,
            Order.PaymentMethod.CREDIT_CARD,
            LocalDateTime.now(),
            List.of(
                    new Product(11L, "Fender Stratocaster", "Legendary electric guitar", BigDecimal.valueOf(1999), 10)
            )
    );


    private static final List<Order> ordersToSave = List.of(
            FIRST_ORDER_LIONEL_MESSI,
            SECOND_ORDER_LIONEL_MESSI,
            SECOND_ORDER,
            THIRD_ORDER,
            FOURTH_ORDER,
            FIFTH_ORDER,
            SIXTH_ORDER,
            SEVENTH_ORDER,
            EIGHTH_ORDER,
            NINTH_ORDER,
            TENTH_ORDER
    );
}