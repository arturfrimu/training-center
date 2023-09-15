package com.arturfrimu.training.center.domain.address;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
