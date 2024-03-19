package com.arturfrimu.training.center.livetemplates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

}