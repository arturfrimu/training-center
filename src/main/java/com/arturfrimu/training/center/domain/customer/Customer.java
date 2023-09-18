package com.arturfrimu.training.center.domain.customer;

import com.arturfrimu.training.center.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "RATING")
    private BigDecimal rating;
    @OneToOne
    @JoinColumn(name = "CUSTOMER_ADDRESS", referencedColumnName = "ID")
    private Address customerAddress;

    public Customer(Long id, String firstName, String lastName, String emailAddress, String phoneNumber, BigDecimal rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public Customer(Long id, String firstName, String lastName, String emailAddress, String phoneNumber, BigDecimal rating, Address customerAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.customerAddress = customerAddress;
    }
}
