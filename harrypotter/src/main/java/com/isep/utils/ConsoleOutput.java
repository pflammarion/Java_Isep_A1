package com.isep.utils;

import com.isep.harrypotter.Potion;
import com.isep.harrypotter.Spell;
import com.isep.harrypotter.characters.Wizard;

import java.util.List;
import java.util.Random;

public class ConsoleOutput implements OutputManager{

    public void displayMessage(String input, Wizard wizard){
        if (wizard.getDrunk() > 0){
            Random random = new Random();
            char[] characters = input.toCharArray();
            int rand = random.nextInt(input.length());
            for(int i = 0; i < rand; i++){
                int randChar = random.nextInt(input.length());
                characters[randChar] = java.lang.Character.toUpperCase(characters[randChar]);
                input = new String(characters);
            }
            System.out.println(input);
        }
        else {
            System.out.println(input);
        }
    }

    public void printAvailablePotions(Wizard wizard){
        displayMessage("You have those potions:", wizard);
        List<Potion> potions = wizard.getPotions();
        for (int i = 0; i < potions.size(); i++) {
            displayMessage((i+1) + ". " + potions.get(i).getName(), wizard);
        }
    }

    public void printKnownSpells(Wizard wizard){
        displayMessage("You know those spells:", wizard);
        List<Spell> knownSpells = wizard.getKnownSpells();
        for (int i = 0; i < knownSpells.size(); i++) {
            displayMessage((i+1) + ". " + knownSpells.get(i).getName(), wizard);
        }
    }

    public void getAvailableSpells(Wizard wizard) {
        List<Spell> allSpells = Spell.getAllSpells();
        allSpells.removeAll(wizard.getKnownSpells());
        displayMessage("All available spells are:", wizard);
        for (int i = 0; i <allSpells.size(); i++) {
            displayMessage((i+1) + ". " + allSpells.get(i).getName(), wizard);
        }
    }
}
