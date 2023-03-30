package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.characters.Enemy;
import com.isep.harrypotter.view.ConsoleOutput;
import com.isep.harrypotter.view.ConsoleParser;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CharacterControllerTest {

    private CharacterController characterController;

    @Before
    public void setUp() {
        ConsoleParser inputParser = new ConsoleParser();
        ConsoleOutput outputManager = new ConsoleOutput();
        SpellController spellController = new SpellController(inputParser, outputManager);
        PotionController potionController = new PotionController(inputParser, outputManager);
        this.characterController = new CharacterController(inputParser, outputManager, spellController, potionController);
    }

    @Test
    public void testBattleEnemy() {
        Enemy enemy = new Enemy(100, 100, 10, 10, 0.2, "The Insatiable Spider");
        assertFalse(characterController.battleEnemy(enemy));
    }

    @Test
    public void testSkippingSchool() {
        assertTrue(characterController.skippingSchool());
    }
    //TODO add more test to better test coverage
}
