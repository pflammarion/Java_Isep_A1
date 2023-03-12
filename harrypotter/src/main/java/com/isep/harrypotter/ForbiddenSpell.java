package com.isep.harrypotter;

import lombok.Getter;

@Getter
public class ForbiddenSpell extends AbstractSpell {
    private String effect;

    public ForbiddenSpell(String name, int energyCost, boolean isForbidden,  String effect) {
        super(name, energyCost, isForbidden);
        this.effect = effect;
    }

    public String castSpell(String spellName, Wizard wizard){
       return "";
    }
}
