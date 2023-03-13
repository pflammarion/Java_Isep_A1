package com.isep.harrypotter;


import com.isep.harrypotter.characters.Wizard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractSpell {
    private String name;
    private int energyCost;
    private boolean isForbidden;

    //TODO % de chance de réussite

    public abstract String castSpell(String spellName, Wizard wizard);

}
