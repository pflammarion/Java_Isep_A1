package com.isep.utils;

import com.isep.harrypotter.spells.Spell;
import com.isep.harrypotter.characters.Wizard;

public interface InputParser {
    int displayMenu();
    Wizard initWizard();
    Spell findSpellByName(Wizard wizard, boolean know);
    Object battleChoice(Wizard wizard);

}
