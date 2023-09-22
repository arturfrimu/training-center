package com.arturfrimu.training.center.spring.rest.entity.driver;

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
@Table(name = "DRIVER")
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
}
