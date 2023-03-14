package com.isep.harrypotter;

import com.isep.harrypotter.characters.AbstractEnemy;
import com.isep.harrypotter.characters.Enemy;
import com.isep.harrypotter.characters.Wizard;
import com.isep.harrypotter.spells.AbstractSpell;
import com.isep.harrypotter.spells.Spell;
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
        this.chapter.setDay(0);
        AbstractEnemy enemy = new Enemy(100, 100, 1, 1);

        while (!isGameFinished){
            if (!chapter.isChapterInit()){
                System.out.println(chapter.getName());
                chapter.setChapterInit(true);
            }
            else if (chapter.getNumber() > 8){
                isGameFinished = true;
            }
            else if (wizard.getDrunk() > 0){
               wizard.setDrunk(wizard.getDrunk() - 1);
            }
            else if (wizard.isNowPet()){
                outputManager.displayMessage("lkjqnsdlkqldsjflqjsdflkjqlmdskfjlqmsjdfljqksfdmljlqksdf ?", wizard);

                //TODO jeu parallèle
            }
            else if (!chapter.isBossPassed() && this.chapter.getDay() > 365){
                playBoss();
                chapter.setBossPassed(true);
                if (chapter.isBossPassed()){
                    chapter.setNumber(chapter.getNumber() + 1);
                    chapter.setChapterInit(false);
                }
                else {
                    outputManager.displayMessage("END GAME...", this.wizard);
                    isGameFinished = true;
                }
            }
            else {
                switch (inputParser.displayMenu()){
                    case 1 -> goToSchool();
                    case 2 -> skippingSchool();
                    case 3 -> battleEnemy(wizard, enemy);
                }
            }
            this.chapter.setDay(chapter.getDay() + new Random().nextInt(21) + 20);
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
            knownSpells.add(new Spell("super forbidden spell", 20, 100));
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

    private void playBoss(){
        //TODO create boss mechanic
        outputManager.displayMessage("This is the time to boss", this.wizard);

    }

    private void battleEnemy(Wizard wizard, AbstractEnemy enemy) {
        //TODO parse a summary
        Battle battle = new Battle(wizard, enemy);
        List<Potion> potionList = wizard.getPotions();
        List<Spell> knownSpell = wizard.getKnownSpells();
        String message;
        if (potionList.size() > 0 && knownSpell.size() > 0){
            message = "You can use " + potionList.size() + " potions and " + knownSpell.size() + " spells";
        }
        else {
            if (potionList.size() > 0){
                message = "You can use " + potionList.size() + " potions";
            } else if (knownSpell.size() > 0) {
                message = "You can use " + knownSpell.size() + " spells";
            }
            else {
                message = "You don't have any potion or spell...";
            }
        }
        if (potionList.size() > 0 || knownSpell.size() > 0){
            outputManager.displayMessage(message, wizard);
            outputManager.displayMessage("Type the name of what you want to use", wizard);
            Object choice = inputParser.battleChoice(wizard);
            if (choice instanceof AbstractSpell spell){
                //TODO cast a spell
                enemy.setCurrentHealth(enemy.getCurrentHealth() - spell.getDamage());
                System.out.println("je suis ici pour cast un spell" + enemy.getCurrentHealth());
            }
            else if (choice instanceof Potion potion){
                potion.drinkPotion(wizard);
                System.out.println("je suis ici pour drink une potion");
                //TODO drink a potion
            }
            else {
                outputManager.displayMessage("Huoohh... it seems to not exist", wizard);
            }
        }

    }
}
