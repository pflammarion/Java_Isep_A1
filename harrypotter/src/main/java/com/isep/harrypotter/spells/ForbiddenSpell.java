package com.isep.harrypotter.spells;

import com.isep.harrypotter.characters.Wizard;
import com.isep.harrypotter.spells.AbstractSpell;
import lombok.Getter;

@Getter
public class ForbiddenSpell extends AbstractSpell {
    private String effect;

    public ForbiddenSpell(String name, int energyCost, int damage,  String effect) {
        super(name, energyCost, damage);
        this.effect = effect;
    }

    public String castSpell(String spellName, Wizard wizard){
       return "";
    }
}
