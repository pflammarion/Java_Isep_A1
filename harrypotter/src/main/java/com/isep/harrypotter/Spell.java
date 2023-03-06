package com.isep.harrypotter;

import java.util.ArrayList;
import java.util.List;

public class Spell extends AbstractSpell {
    public Spell(String name, int energyCost) {
        super(name, energyCost, true);
    }


    private static List<Spell> getAllSpells() {
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

    public static List<Spell> getAvailableSpells(Wizard wizard) {
        List<Spell> allSpells = getAllSpells();
        allSpells.removeAll(wizard.getKnownSpells());
        return allSpells;
    }
    @Override
    public void castSpell(Wizard wizard) {
        // TODO: Implement the logic for casting a forbidden spell
    }

}
