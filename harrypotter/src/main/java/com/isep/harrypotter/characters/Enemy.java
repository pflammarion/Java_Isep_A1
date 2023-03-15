package com.isep.harrypotter.characters;

import com.isep.harrypotter.characters.AbstractEnemy;

public class Enemy extends AbstractEnemy {
    public Enemy(int totalHealth, double currentHealth, int defence, int damage, String name) {
        super(totalHealth, currentHealth, defence, damage, name);
    }
}
