package com.isep.harrypotter.view;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.model.characters.Wizard;

import java.util.List;
import java.util.Random;

public class ConsoleOutput implements OutputManager{

    public void displayMessage(String input, int drunkDays){
        if (drunkDays > 0){
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

    public void print(String input){
        System.out.println(input);
    }

    public void showListElements(String introducer, List<Object> list, int drunkDays) {
        displayMessage(introducer, drunkDays);
        int index = 1;
        for (Object element : list) {
            displayMessage(index + ". " + element.toString(), drunkDays);
            index++;
        }
    }

    public void printAvailablePotions(List<Potion> potions, int drunkDays){
        displayMessage("You have those potions:", drunkDays);
        for (int i = 0; i < potions.size(); i++) {
            displayMessage((i+1) + ". " + potions.get(i).getName(), drunkDays);
        }
    }

    public void printKnownSpells(List<Spell> knownSpells, int drunkDays){
        displayMessage("You know those spells:", drunkDays);
        for (int i = 0; i < knownSpells.size(); i++) {
            displayMessage((i+1) + ". " + knownSpells.get(i).getName(), drunkDays);
        }
    }

    public void getAvailableSpells(List<Spell> knownSpells, int drunkDays) {
        List<Spell> allSpells = Spell.getAllSpells();
        allSpells.removeAll(knownSpells);
        displayMessage("All available spells are:", drunkDays);
        for (int i = 0; i <allSpells.size(); i++) {
            displayMessage((i+1) + ". " + allSpells.get(i).getName(), drunkDays);
        }
    }
}
