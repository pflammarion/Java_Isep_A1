package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.others.Core;
import com.isep.harrypotter.model.others.House;
import com.isep.harrypotter.model.others.Pet;
import com.isep.harrypotter.model.others.Wand;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.ConsoleOutput;
import com.isep.harrypotter.view.ConsoleParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SpellControllerTest {

    SpellController spellController;

    private Wizard wizard;

    @BeforeEach
    void setUp() {
        ConsoleParser inputParser = new ConsoleParser(System.in);
        ConsoleOutput outputManager = new ConsoleOutput();
        spellController = new SpellController(inputParser, outputManager);
        wizard = new Wizard(Pet.CAT, new Wand(Core.DRAGON_HEARTSTRING, 20), House.GRYFFINDOR, new ArrayList<>(), new HashMap<>(), "Harry", "Potter", 1, false, 4, new Random(), new ArrayList<>());
    }

    @Test
    void getAllSpells() {
        List<AbstractSpell> spellList = spellController.getAllSpells();
        assertEquals(29, spellList.size());
    }

    @Test
    void getAllKnownSpells() {
        spellController.learnSpell(spellController.getSpells().get(1), wizard);
        List<AbstractSpell> spellList = spellController.getAllKnownSpells(wizard);
        assertEquals(1, spellList.size());
    }

    @Test
    void getKnownSpells() {
        spellController.learnSpell(spellController.getSpells().get(1), wizard);
        spellController.learnSpell(spellController.getSpells().get(2), wizard);
        List<Spell> spellList = spellController.getKnownSpells(wizard);
        assertEquals(2, spellList.size());
    }

    @Test
    void getKnownSpellByName() {
        spellController.learnSpell(spellController.getSpells().get(1), wizard);
        Spell spell =  spellController.getKnownSpellByName("Lumos", wizard);
        assertNotNull(spell);
    }

    @Test
    void getAvailableSpellByName() {
        assertNotNull(spellController.getAvailableSpellByName("Lumos", wizard, 1));
        assertNull(spellController.getAvailableSpellByName("Reducto", wizard, 1));
    }

    @Test
    void learnSpell() {
        spellController.learnSpell(spellController.getSpells().get(1), wizard);
        List<AbstractSpell> spellList = spellController.getAllKnownSpells(wizard);
        assertEquals(1, spellList.size());
    }
}