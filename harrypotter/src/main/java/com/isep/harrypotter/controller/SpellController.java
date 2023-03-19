package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.ForbiddenSpell;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SpellController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private List<AbstractSpell> spells;

    public SpellController(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;

        spells = new ArrayList<>();
        spells.add(new ForbiddenSpell("Cruciatus Curse", "A curse that causes intense pain", 100, 20, "Super effect"));
        spells.add(new Spell("Wingardium Leviosa", "A charm that levitates objects", 50, 10));
        spells.add(new Spell("Lumos", "A charm that creates light at the tip of the wand", 10, 0));
        spells.add(new Spell("Alohomora", "A charm that unlocks and opens doors", 30, 0));
        spells.add(new Spell("Expecto Patronum", "A charm that summons a guardian to protect against Dementors", 3, 1));
        spells.add(new Spell("Expelliarmus", "A spell that disarms opponents by causing their wand to fly out of their hand", 4, 1));
        spells.add(new Spell("Avada Kedavra", "A curse that causes instant death", 6, 1));
    }

    public List<AbstractSpell> getAllSpells() {
        return spells;
    }

    public List<AbstractSpell> getAllKnownSpells(Wizard wizard) {
        List<AbstractSpell> knownSpells = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (wizard.getKnownSpells().contains(spell)) {
                knownSpells.add(spell);
            }
        }
        return knownSpells;
    }


    public List<Spell> getKnownSpells(Wizard wizard) {
        List<Spell> knownSpells = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && wizard.getKnownSpells().contains(spell)) {
                knownSpells.add((Spell) spell);
            }
        }
        return knownSpells;
    }

    public List<ForbiddenSpell> getForbiddenSpells() {
        List<ForbiddenSpell> forbiddenSpells = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (spell instanceof ForbiddenSpell) {
                forbiddenSpells.add((ForbiddenSpell) spell);
            }
        }
        return forbiddenSpells;
    }

    public List<Spell> getSpells() {
        List<Spell> spellList = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell) {
                spellList.add((Spell) spell);
            }
        }
        return spellList;
    }

    public Spell getKnownSpellByName(String spellName, Wizard wizard) {
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && spell.getName().equalsIgnoreCase(spellName) && wizard.getKnownSpells().contains(spell)) {
                return (Spell) spell;
            }
        }
        return null;
    }

    public Spell getAvailableSpellByName(String spellName, Wizard wizard) {
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && spell.getName().equalsIgnoreCase(spellName) && !wizard.getKnownSpells().contains(spell)) {
                return (Spell) spell;
            }
        }
        return null;
    }
    public void learnSpell(AbstractSpell spell, Wizard wizard){
        List<AbstractSpell> knownSpells = getAllKnownSpells(wizard);
        knownSpells.add(spell);
        wizard.setKnownSpells(knownSpells);
        outputManager.displayMessage("You have learned the " + spell.getName() +" spell!\n", wizard.getDrunk());
    }

}
