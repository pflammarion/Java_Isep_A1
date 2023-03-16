package com.isep.harrypotter.spells;

import com.isep.harrypotter.characters.Wizard;

import java.util.ArrayList;
import java.util.List;

public class Spell extends AbstractSpell {
    public Spell(String name, int energyCost, int damage) {
        super(name, energyCost, damage);
    }


    public static List<Spell> getAllSpells() {
        List<Spell> spells = new ArrayList<>();

        // Create spells and add them to the list
        spells.add(new Spell("Wingardium Leviosa", 50, 10));
        spells.add(new Spell("Lumos", 10, 0));
        spells.add(new Spell("Alohomora", 30, 0));
        spells.add(new Spell("Expecto Patronum", 3, 1));
        spells.add(new Spell("Expelliarmus", 4, 1));
        spells.add(new Spell("Wingardium Leviosa", 5, 1));
        spells.add(new Spell("Avada Kedavra", 6, 1));

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
