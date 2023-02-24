package com.isep.harrypotter;

import java.util.ArrayList;
import java.util.List;

public class SpellList {

    public static List<Spell> getAllSpells() {
        List<Spell> spells = new ArrayList<>();

        // Create spells and add them to the list
        spells.add(new Spell("Lumos", 1));
        spells.add(new Spell("Alohomora", 2));
        spells.add(new Spell("Expecto Patronum", 3));
        spells.add(new Spell("Expelliarmus", 4));
        spells.add(new Spell("Wingardium Leviosa", 5));
        spells.add(new Spell("Avada Kedavra", 6));

        return spells;
    }
}