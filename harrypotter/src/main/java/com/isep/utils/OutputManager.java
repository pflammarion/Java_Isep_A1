package com.isep.utils;

import com.isep.harrypotter.Wizard;

public interface OutputManager {
    void displayMessage(String message, Wizard wizard);
    void printAvailablePotions(Wizard wizard);
    void printKnownSpells(Wizard wizard);
    void getAvailableSpells(Wizard wizard);
}
