package com.arturfrimu.training.center.domain.order;

import com.arturfrimu.training.center.domain.address.Address;
import com.arturfrimu.training.center.domain.customer.Customer;
import com.arturfrimu.training.center.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.Collections.unmodifiableList;
import static lombok.AccessLevel.NONE;

@Getter
@Setter
@ToString(exclude = "products")
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "ID")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "SHIPPING_ADDRESS", referencedColumnName = "ID")
    private Address shippingAddress;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal productPrice;
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Enumerated(value = STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;
    @Enumerated(value = STRING)
    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;
    @Column(name = "ORDER_DATE_AND_TIME")
    private LocalDateTime orderDateAndTime;
    @Getter(NONE)
    @OneToMany(mappedBy = "order")
    private List<Product> products;

    public Order(Long id, Customer customer, Address shippingAddress, String productName, BigDecimal productPrice, BigDecimal totalAmount, Integer quantity, OrderStatus orderStatus, PaymentMethod paymentMethod, LocalDateTime orderDateAndTime) {
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.orderDateAndTime = orderDateAndTime;
    }

    public List<Product> getProducts() {
        return unmodifiableList(products);
    }

    public void addProduct(Product product) {
        if (Objects.isNull(products)) {
            products = new ArrayList<>();
        }
        if (Objects.nonNull(product)) {
            products.add(product);
        }
    }

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELED
    }

    public enum PaymentMethod {
        CREDIT_CARD, PAYPAL, CASH_ON_DELIVERY
    }
}
