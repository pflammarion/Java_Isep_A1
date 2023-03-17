package com.isep.harrypotter.model.characters;

public class Enemy extends AbstractEnemy {
    public Enemy(int totalHealth, double currentHealth, int defence, int damage , double accuracy, String name) {
        super(totalHealth, currentHealth, defence, damage, accuracy, name);
    }
}
