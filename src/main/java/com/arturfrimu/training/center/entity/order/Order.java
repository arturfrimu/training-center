package com.arturfrimu.training.center.entity.order;

import com.arturfrimu.training.center.entity.address.Address;
import com.arturfrimu.training.center.entity.customer.Customer;
import com.arturfrimu.training.center.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public Order(Long id, Customer customer, Address shippingAddress, OrderStatus orderStatus, PaymentMethod paymentMethod, LocalDateTime orderDateAndTime, List<Product> products) {
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.orderDateAndTime = orderDateAndTime;
        this.products = products;
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
