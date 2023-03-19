package com.isep.harrypotter.model.spells;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.Getter;

public class ForbiddenSpell extends AbstractSpell {
    public ForbiddenSpell(String name, int energyCost, int damage,  String effect) {
        super(name, energyCost, damage);
    }

}
