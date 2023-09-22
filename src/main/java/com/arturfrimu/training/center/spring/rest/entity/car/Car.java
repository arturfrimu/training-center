package com.arturfrimu.training.center.spring.rest.entity.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAR")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
}
