package com.isep.utils;

import com.isep.harrypotter.Spell;
import com.isep.harrypotter.Wizard;

public interface InputParser {
    int displayMenu();
    Wizard initWizard();
    Spell findSpellByName(Wizard wizard, boolean know);

}
