package com.isep.harrypotter.model.spells;


import com.isep.harrypotter.model.characters.Wizard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractSpell {
    private String name;
    private int energyCost;
    private int damage;

    //TODO % de chance de réussite


}
