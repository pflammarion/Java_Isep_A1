package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.AbstractEnemy;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;

import java.util.List;
import java.util.Random;

public class Game {
    private final InputParser inputParser;
    private final OutputManager outputManager;
    private boolean isGameFinished;
    private final CharacterController characterController;
    private final ChapterController chapterController;
    private final SpellController spellController;

    public Game(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.isGameFinished = false;
        this.characterController = new CharacterController(inputParser, outputManager, new Wizard(), new Random());
        this.chapterController = new ChapterController(inputParser, outputManager, new Chapter(1));
        this.spellController = new SpellController(inputParser, outputManager);
    }

    public void play(){
        characterController.initWizard();
        chapterController.initChapter();

        while (!isGameFinished){
            isGameFinished = chapterController.newDay();
            characterController.soberUp();
            //TODO pet wizard

            if (chapterController.isChapterFinish()){
                boolean victory = characterController.battleEnemy(chapterController.initBoss());
                isGameFinished = chapterController.nextChapter(victory);
            }
            else {
                switch (displayMenu()){
                    case 1 -> goToSchool();
                    case 2 -> characterController.skippingSchool();
                    case 3 -> isGameFinished = !characterController.battleEnemy(chapterController.initBoss());
                }
            }
        }
        outputManager.displayMessage("C'est fini sort de chez toi pour voir le dehors.", characterController.getWizard());
        System.exit(1);
    }


    private void goToSchool(){
        this.outputManager.getAvailableSpells(characterController.getWizard());
        this.outputManager.displayMessage("Enter the name of the spell you want to learn", characterController.getWizard());
        Spell spell = spellController.getSpellByName(characterController.getWizard(), false);
        spellController.setSpell(spell);
        spellController.learnSpell(characterController.getWizard());
        this.outputManager.printKnownSpells(characterController.getWizard());
    }


    private int displayMenu(){
        System.out.println("\nWhat a nice day, what are you going to do today ?");
        System.out.println("1. Go to school");
        System.out.println("2. Skipping school");
        return inputParser.getInt("Please enter an available proposition");
    }
}
