package com.isep.harrypotter.characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Character {

    private int totalHealth;
    private double currentHealth;
    private int defence;
    private int damage;
    public void attack(Character character){}
}
