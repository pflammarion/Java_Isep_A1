package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SpellController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private Spell spell;

    public SpellController(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;
    }

    public void castSpell(String spellName, Wizard wizard){
        Spell spell = Spell.loopInSpell(spellName, wizard, true);
        if (spell == null){
            outputManager.displayMessage("You don't know the spell " + spellName + "!", wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You cast the spell " + spell.getName(), wizard.getDrunk());
        }
    }
    public void learnSpell(Wizard wizard){
        List<Spell> knownSpells = wizard.getKnownSpells();
        knownSpells.add(this.spell);
        wizard.setKnownSpells(knownSpells);
        outputManager.displayMessage("You have learned the " + this.spell.getName() +" spell!\n", wizard.getDrunk());
    }

    public Spell getSpellByName(Wizard wizard, boolean know){
        return Spell.loopInSpell(inputParser.getString(wizard), wizard, know);
    }

    private void displaySpells(Wizard wizard) {
        System.out.println("\nYou know those spells:");
        List<Spell> knownSpells = wizard.getKnownSpells();
        if(knownSpells.size() == 0){
            System.out.println("You don't know any spell");
        }
        else {
            for (int i = 0; i < knownSpells.size(); i++) {
                System.out.println((i+1) + ". " + knownSpells.get(i).getName());
            }
        }

        System.out.println("\nYou can continue the previous action\n\n");
    }
}
