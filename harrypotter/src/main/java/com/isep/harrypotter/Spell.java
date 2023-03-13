package com.isep.harrypotter;

import com.isep.harrypotter.characters.Wizard;

import java.util.ArrayList;
import java.util.List;

public class Spell extends AbstractSpell {
    public Spell(String name, int energyCost) {
        super(name, energyCost, true);
    }


    public static List<Spell> getAllSpells() {
        List<Spell> spells = new ArrayList<>();

        // Create spells and add them to the list
        spells.add(new Spell("Wingardium Leviosa", 1));
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

    public static Spell loopInSpell(String spellName, Wizard wizard, Boolean known) {
        Spell spell = null;
        List<Spell> wizz;
        if (known){
            wizz = wizard.getKnownSpells();
        }
        else wizz = getAvailableSpells(wizard);
        for (Spell s : wizz) {
            if (s.getName().equalsIgnoreCase(spellName)) {
                spell = s;
                break;
            }
        }
        return spell;
    }

    public String castSpell(String spellName, Wizard wizard){
        Spell spell = loopInSpell(spellName, wizard, true);
        if (spell == null){
            return "You don't know the spell " + spellName + "!";
        }
        else {
            return "You cast the spell " + spell.getName();
        }
    }

    public String learnSpell(Wizard wizard){
        List<Spell> knownSpells = wizard.getKnownSpells();
        knownSpells.add(this);
        wizard.setKnownSpells(knownSpells);
        return "You have learned the " + this.getName() +" spell!\n";
    }
}
