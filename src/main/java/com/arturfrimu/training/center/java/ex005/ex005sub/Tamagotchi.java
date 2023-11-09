package com.arturfrimu.training.center.java.ex005.ex005sub;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

/**
 * Bad  entity with public getters and setters for all fields, public no arg constructor, no factory methods
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Tamagotchi {
    @Id
    private UUID id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pocket_id")
    private Pocket pocket;
}
