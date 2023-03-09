package com.isep.harrypotter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Potion {
    private String name;
    private int healthPoint;

    public void drinkPotion(Wizard wizard){
        int health = wizard.getCurrentHealth() + this.healthPoint;
        int totalHealth = wizard.getTotalHealth();
        int heal = Math.min(health, totalHealth);
        if (wizard.randomProbability(100)){
            wizard.setCurrentHealth(0);
        }
        else {
            wizard.setCurrentHealth(heal);
        }

    }
}
