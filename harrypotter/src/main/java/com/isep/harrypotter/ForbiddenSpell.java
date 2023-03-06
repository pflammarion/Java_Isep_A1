package com.isep.harrypotter;

import lombok.Getter;

@Getter
public class ForbiddenSpell extends AbstractSpell {
    private String effect;

    public ForbiddenSpell(String name, int energyCost, boolean isForbidden,  String effect) {
        super(name, energyCost, isForbidden);
        this.effect = effect;
    }

    @Override
    public void castSpell(Wizard wizard) {
        // TODO: Implement the logic for casting a forbidden spell
    }
}
