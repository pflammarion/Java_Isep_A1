package com.isep.harrypotter.model.spells;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Spell extends AbstractSpell {
    public Spell(String name, String description, int energyCost, int damage, int minimumChapter) {
        super(name, description, energyCost, damage, minimumChapter);
    }

    @Override
    public String toString() {
        return getName();
    }
}
