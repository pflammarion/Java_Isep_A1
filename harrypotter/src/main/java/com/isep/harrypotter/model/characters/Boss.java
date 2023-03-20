package com.isep.harrypotter.model.characters;

public class Boss extends AbstractEnemy {

    private String specialObject;
    private String specialSpell;

    public Boss(int totalHealth, double currentHealth, int defense, int damage, double accuracy, String name, String specialObject, String specialSpell) {
        super(totalHealth, currentHealth, defense, damage, accuracy, name);
        this.specialObject = specialObject;
        this.specialSpell = specialSpell;
    }
}
