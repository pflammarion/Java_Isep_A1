package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.others.Core;
import com.isep.harrypotter.model.others.House;
import com.isep.harrypotter.model.others.Pet;
import com.isep.harrypotter.model.others.Wand;
import com.isep.harrypotter.view.ConsoleOutput;
import com.isep.harrypotter.view.ConsoleParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PotionControllerTest {
    private PotionController potionController;
    private Wizard wizard;

    @BeforeEach
    void setUp() {
        ConsoleParser inputParser = new ConsoleParser();
        ConsoleOutput outputManager = new ConsoleOutput();
        potionController = new PotionController(inputParser, outputManager);

        // Create a wizard with some known potions
        List<Potion> potions = new ArrayList<>();
        potions.add(new Potion("Elixir of Life", "Doubles the drinker's current health", "health", 0, 10, 2));
        potions.add(new Potion("Healing Salve", "Restores a moderate amount of the drinker's health", "health", 50, 2, 1));

        wizard = new Wizard(Pet.CAT, new Wand(Core.DRAGON_HEARTSTRING, 20), House.GRYFFINDOR, new ArrayList<>(), new HashMap<>(), "Harry", "Potter", 1, false, 4, new Random(), new ArrayList<>());
        Potion potion1 = potions.get(0);
        Potion potion2 = potions.get(1);
        Map<Potion, Integer> knownPotions = potionController.getKnownPotions(wizard);
        knownPotions.put(potion1, 1);
        knownPotions.put(potion2, 1);
        wizard.setPotions(knownPotions);
    }

    @Test
    void getAllAvailablePotions() {
        List<Potion> availablePotions = potionController.getAllAvailablePotions(1);
        assertEquals(3, availablePotions.size());
        Potion potion1 = new Potion("Healing Salve", "Restores a moderate amount of the drinker's health", "health", 50, 2, 1);
        Potion potion2 =  new Potion("Elixir of Life", "Doubles the drinker's current health", "health", 0, 10, 2);
        assertNotNull(potionController.getAvailablePotionByName(potion1.getName(), 1));
        assertNull(potionController.getAvailablePotionByName(potion2.getName(), 1));
    }

    @Test
    void getKnownPotionByName() {
        Potion potion = potionController.getKnownPotionByName("Healing Salve", this.wizard);
        assertNotNull(potion);
        assertEquals("Healing Salve", potion.getName());
        assertNotNull(potionController.getKnownPotionByName("Elixir of Life", wizard));
    }

    @Test
    void getAvailablePotionByName() {
        Potion potion = potionController.getAvailablePotionByName("Healing Salve", 1);
        assertNotNull(potion);
        assertEquals("Healing Salve", potion.getName());
        assertNull(potionController.getAvailablePotionByName("Elixir of Life", 1));
        assertNotNull(potionController.getAvailablePotionByName("Healing Salve", 1));
    }

    @Test
    void learnPotion() {
        wizard.setPotions(new HashMap<>());
        Potion potion = potionController.getAvailablePotionByName("Healing Salve", 1);
        potionController.learnPotion(potion, wizard);
        assertTrue(wizard.getPotions().size() > 0);
    }
}