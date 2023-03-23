package com.isep.harrypotter.view;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.Spell;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface OutputManager {
    void displayMessage(String message, int drunkDays);
    void showListElements(String introducer, List<?> list, int drunkDays);
    void print(String input);
    void readHelperFile();
}
