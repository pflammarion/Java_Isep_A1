package com.isep.harrypotter.model.spells;

public class Spell extends AbstractSpell {
    public Spell(String name, String description, int energyCost, int damage, int minimumChapter) {
        super(name, description, energyCost, damage, minimumChapter);
    }

    @Override
    public String toString() {
        return getName();
    }
}
