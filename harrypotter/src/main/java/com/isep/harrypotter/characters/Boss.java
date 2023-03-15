package com.isep.harrypotter.characters;

import com.isep.harrypotter.Inventory;
import com.isep.harrypotter.spells.Spell;

public class Boss extends AbstractEnemy {

    private String specialObject;
    private String specialSpell;

    public Boss(int totalHealth, double currentHealth, int defence, int damage, String name, String specialObject, String specialSpell) {
        super(totalHealth, currentHealth, defence, damage, name);
        this.specialObject = specialObject;
        this.specialSpell = specialSpell;
    }
}
