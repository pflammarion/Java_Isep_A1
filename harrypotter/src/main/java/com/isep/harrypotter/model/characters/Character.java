package com.isep.harrypotter.model.characters;

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
    private int defense;
    private int damage;
    private double accuracy;
    public void attack(Character character){}

    public void takeDamage(double damage){
        setCurrentHealth(getCurrentHealth() - damage);
    }
}
