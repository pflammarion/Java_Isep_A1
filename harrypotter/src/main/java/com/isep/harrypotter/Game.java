package com.isep.harrypotter;

import com.isep.utils.InputParser;
import com.isep.utils.OutputManager;
import java.util.List;
import java.util.Random;

public class Game {
    private InputParser inputParser;
    private OutputManager outputManager;

    private Wizard wizard;
    private Chapter chapter;

    private boolean isGameFinished;

    public Game(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.chapter = new Chapter();
        this.isGameFinished = false;
        this.wizard = this.inputParser.initWizard();
    }

    public void play(){

        while (!isGameFinished){
            if (!chapter.isChapterInit()){
                System.out.println(chapter.getName());
                chapter.setChapterInit(true);
            }
            if (chapter.getNumber() > 8){
                isGameFinished = true;
            }
            if (wizard.getDrunk() > 0){
               wizard.setDrunk(wizard.getDrunk() - 1);
            }
            if (wizard.isNowPet()){
                outputManager.displayMessage("lkjqnsdlkqldsjflqjsdflkjqlmdskfjlqmsjdfljqksfdmljlqksdf ?", wizard);

                //TODO jeu parallèle
            }
            else {
                switch (inputParser.displayMenu()){
                    case 1 -> goToSchool();
                    case 2 -> skippingSchool();
                }
            }
        }
    }

    public void skippingSchool(){
        this.outputManager.displayMessage("You decided to skip school.", this.wizard);
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just found a new potion", this.wizard);
            List<Potion> potions = this.wizard.getPotions();
            potions.add(new Potion("super potion", "Potion to get hp", 3, "health", 30));
            this.wizard.setPotions(potions);
            this.outputManager.printAvailablePotions(this.wizard);
        }
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just learned a new spell", this.wizard);
            List<Spell> knownSpells = this.wizard.getKnownSpells();
            knownSpells.add(new Spell("super forbidden spell", 20));
            this.wizard.setKnownSpells(knownSpells);
            this.outputManager.printKnownSpells(this.wizard);
        }
    }

    public void goToSchool(){
        this.outputManager.getAvailableSpells(this.wizard);
        this.outputManager.displayMessage("Enter the name of the spell you want to learn", this.wizard);
        Spell spell = this.inputParser.findSpellByName(this.wizard, false);
        this.outputManager.displayMessage(spell.learnSpell(wizard), this.wizard);
        this.outputManager.printKnownSpells(this.wizard);
    }

    private boolean randomProbability(int chance){
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }
}
