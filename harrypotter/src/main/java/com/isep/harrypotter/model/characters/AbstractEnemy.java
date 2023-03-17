package com.isep.harrypotter.model.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractEnemy extends Character {
    private String name;
    public AbstractEnemy(int totalHealth, double currentHealth, int defence, int damage, double accuracy, String name) {
        super(totalHealth, currentHealth, defence, damage, accuracy);
        this.name = name;
    }
}
