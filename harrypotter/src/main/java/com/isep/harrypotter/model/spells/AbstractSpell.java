package com.isep.harrypotter.model.spells;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractSpell {
    private String name;
    private String description;
    private int energyCost;
    private int damage;
    private int minimumChapter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSpell that = (AbstractSpell) o;
        return damage == that.damage && Objects.equals(name, that.name) && energyCost == that.energyCost;
    }

}
