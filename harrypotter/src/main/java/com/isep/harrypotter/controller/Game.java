package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.Wizard;
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
        this.characterController = new CharacterController(inputParser, outputManager, spellController, potionController);
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
                outputManager.displayMessage("Oh, what is happenning ...? A BOSS ???\n", characterController.getWizard().getDrunk());
                boolean victory = characterController.battleEnemy(chapterController.initBoss());
                isGameFinished = chapterController.nextChapter(victory);
            }
            else {
                switch (displayMenu()){
                    case 1 -> goToSchool();
                    case 2 -> isGameFinished = !characterController.skippingSchool();
                    //case 3 -> isGameFinished = !characterController.battleEnemy(new Enemy(100, 10, 10, 10, 0.1, "Cha"));
                }
                outputManager.progressPercentage(chapterController.getChapter().getDay(), 365, "day");
            }
        }
        outputManager.displayMessage("C'est fini sort de chez toi pour voir le dehors.", characterController.getWizard().getDrunk());
        System.exit(1);
    }


    private void goToSchool(){
        Wizard wizard = characterController.getWizard();
        outputManager.displayMessage("You can learn a spell or a potion, which book do you want to open?", wizard.getDrunk());
        String choice = characterController.getString(wizard);
        if (choice.equals("potion") || choice.equals("p") || choice.equals("potions")){
            this.outputManager.showListElements("All available potions are:", potionController.getAllAvailablePotions(chapterController.getChapter().getNumber()), wizard.getDrunk());
            this.outputManager.displayMessage("Enter the name of the potion you want to learn", wizard.getDrunk());
            String input = characterController.getString(wizard);
            Potion potion = potionController.getAvailablePotionByName(input, chapterController.getChapter().getNumber());
            if (null != potion){
                potionController.learnPotion(potion, wizard);
                this.outputManager.showMapElements("You have those potions:", wizard.getPotions(), wizard.getDrunk());
            }
            else {
                outputManager.displayMessage("You learned useless things today", wizard.getDrunk());
            }
        }
        else if (choice.equals("s") || choice.equals("spell") || choice.equals("spells")){
            this.outputManager.showListElements("All available spells are:", spellController.getSpells(chapterController.getChapter().getNumber()), wizard.getDrunk());
            this.outputManager.displayMessage("Enter the name of the spell you want to learn", wizard.getDrunk());
            String input = characterController.getString(wizard);
            Spell spell = spellController.getAvailableSpellByName(input, wizard, chapterController.getChapter().getNumber());
            if (null != spell){
                spellController.learnSpell(spell, wizard);
                this.outputManager.showListElements("You know those spells:", wizard.getKnownSpells(), wizard.getDrunk());
            }
            else {
                outputManager.displayMessage("You learned useless things today", wizard.getDrunk());
            }
        }
        else {
            outputManager.displayMessage("You haven't find any book today... Let's go to sleep then....", wizard.getDrunk());

        }

    }


    private int displayMenu(){
        System.out.println("\nWhat a nice day, what are you going to do today ?");
        System.out.println("1. Go to school");
        System.out.println("2. Skipping school");
        return inputParser.getInt("Please enter an available proposition");
    }
}
