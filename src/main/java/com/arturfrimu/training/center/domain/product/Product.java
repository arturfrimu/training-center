package com.arturfrimu.training.center.domain.product;

import com.arturfrimu.training.center.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
}
