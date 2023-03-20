package com.isep.harrypotter.model.characters;

public class Enemy extends AbstractEnemy {
    public Enemy(int totalHealth, double currentHealth, int defense, int damage , double accuracy, String name) {
        super(totalHealth, currentHealth, defense, damage, accuracy, name);
    }
}
