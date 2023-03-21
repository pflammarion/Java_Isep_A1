package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.AbstractEnemy;
import com.isep.harrypotter.model.characters.Enemy;
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
    private final PotionController potionController;

    public Game(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.isGameFinished = false;
        this.spellController = new SpellController(inputParser, outputManager);
        this.potionController= new PotionController(inputParser, outputManager);
        this.characterController = new CharacterController(inputParser, outputManager, spellController, potionController, new Wizard(), new Random());
        this.chapterController = new ChapterController(inputParser, outputManager, new Chapter(1));
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
                    case 3 -> isGameFinished = !characterController.battleEnemy(new Enemy(100, 10, 10, 10, 0.1, "Cha"));
                }
            }
        }
        outputManager.displayMessage("C'est fini sort de chez toi pour voir le dehors.", characterController.getWizard().getDrunk());
        System.exit(1);
    }


    private void goToSchool(){
        Wizard wizard = characterController.getWizard();
        this.outputManager.showListElements("All available spells are:", spellController.getSpells(), wizard.getDrunk());
        this.outputManager.displayMessage("Enter the name of the spell you want to learn", wizard.getDrunk());
        Spell spell = spellController.getAvailableSpellByName(characterController.getString(wizard), wizard);
        if (null != spell){
            spellController.learnSpell(spell, wizard);
            this.outputManager.showListElements("You know those spells:", wizard.getKnownSpells(), wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You learned useless things today", wizard.getDrunk());
        }
    }


    private int displayMenu(){
        outputManager.print("\nWhat a nice day, what are you going to do today ?");
        outputManager.print("1. Go to school");
        outputManager.print("2. Skipping school");
        return inputParser.getInt("Please enter an available proposition");
    }
}
