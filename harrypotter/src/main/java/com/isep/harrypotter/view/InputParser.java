package com.isep.harrypotter.view;

import com.isep.harrypotter.model.characters.Wizard;

public interface InputParser {
    int getInt(String messageWhenMismatch);
    String getString(Wizard wizard);

}
