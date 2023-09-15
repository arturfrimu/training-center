package com.arturfrimu.training.center.domain.order;

import com.arturfrimu.training.center.domain.address.Address;
import com.arturfrimu.training.center.domain.customer.Customer;
import com.arturfrimu.training.center.domain.product.Product;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static lombok.AccessLevel.NONE;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    private Long id;
    private Customer customer;
    private Address shippingAddress;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal totalAmount;
    private Integer quantity;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderDateAndTime;
    @Getter(NONE)
    private List<Product> products;

    public List<Product> getProducts() {
        return unmodifiableList(products);
    }

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELED
    }

    public enum PaymentMethod {
        CREDIT_CARD, PAYPAL, CASH_ON_DELIVERY
    }
}
