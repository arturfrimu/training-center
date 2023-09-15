package com.arturfrimu.training.center.domain.customer;

import com.arturfrimu.training.center.domain.address.Address;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private Address customerAddress;
}
