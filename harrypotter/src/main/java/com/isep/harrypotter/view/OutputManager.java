package com.isep.harrypotter.view;

import com.isep.harrypotter.model.characters.Wizard;

import java.util.List;

public interface OutputManager {
    void displayMessage(String message, Wizard wizard);
    void showListElements(String introducer, List<Object> list, Wizard wizard);
    void printAvailablePotions(Wizard wizard);
    void printKnownSpells(Wizard wizard);
    void getAvailableSpells(Wizard wizard);
    void print(String input);
}
