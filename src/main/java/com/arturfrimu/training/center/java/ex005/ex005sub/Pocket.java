package com.arturfrimu.training.center.java.ex005.ex005sub;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Good entity with private getters and setters for hibernate, protected no args constructor and factory method
 */
@Entity
@Setter(PRIVATE)
@Getter(PRIVATE)
public class Pocket {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "pocket")
    private List<Tamagotchi> tamagotchis = new ArrayList<>();

    protected Pocket() {}

    private Pocket(String name) {
        this.name = name;
    }

    public Pocket createInstance(String pocketName) {
        return new Pocket(pocketName);
    }
}
