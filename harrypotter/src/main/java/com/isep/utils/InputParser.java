package com.isep.utils;

import com.isep.harrypotter.Spell;
import com.isep.harrypotter.characters.Wizard;

public interface InputParser {
    int displayMenu();
    Wizard initWizard();
    Spell findSpellByName(Wizard wizard, boolean know);

}
