package com.isep.harrypotter;

public class ForbiddenSpell extends AbstractSpell {
    private String effect;

    public ForbiddenSpell(String name, int energyCost, String effect) {
        super(name, energyCost);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    @Override
    public void castSpell(Wizard wizard) {
        // TODO: Implement the logic for casting a forbidden spell
    }
}
