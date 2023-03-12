package com.isep.harrypotter;


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

    public abstract String castSpell(String spellName,Wizard wizard);

}
