package com.arturfrimu.training.center.entity.product;

import com.arturfrimu.training.center.entity.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public Product(Long id, String name, String description, BigDecimal price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
