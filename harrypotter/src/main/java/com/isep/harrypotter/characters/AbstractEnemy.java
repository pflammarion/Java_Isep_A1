package com.isep.harrypotter.characters;

import com.isep.harrypotter.characters.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractEnemy extends Character {
    private String name;
    public AbstractEnemy(int totalHealth, double currentHealth, int defence, int damage) {
        super(totalHealth, currentHealth, defence, damage);
    }
}
