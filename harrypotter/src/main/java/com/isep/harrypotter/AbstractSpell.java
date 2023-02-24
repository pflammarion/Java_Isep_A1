package com.isep.harrypotter;

public abstract class AbstractSpell {
    private String name;
    private int energyCost;

    public AbstractSpell(String name, int energyCost) {
        this.name = name;
        this.energyCost = energyCost;
    }

    public String getName() {
        return name;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public abstract void castSpell(Wizard wizard);

}
