package com.isep.harrypotter.view;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.Spell;

import java.util.List;

public interface OutputManager {
    void displayMessage(String message, int drunkDays);
    void showListElements(String introducer, List<Object> list, int drunkDays);
    void printAvailablePotions(List<Potion> potions, int drunkDays);
    void printKnownSpells(List<Spell> knownSpells, int drunkDays);
    void getAvailableSpells(List<Spell> knownSpells, int drunkDays);
    void print(String input);
}
