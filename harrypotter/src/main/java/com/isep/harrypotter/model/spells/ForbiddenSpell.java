package com.isep.harrypotter.model.spells;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.Getter;

public class ForbiddenSpell extends AbstractSpell {
    private String effect;

    public ForbiddenSpell(String name, String description, int energyCost, int damage,  String effect) {
        super(name, description, energyCost, damage);
        this.effect = effect;
    }
}
