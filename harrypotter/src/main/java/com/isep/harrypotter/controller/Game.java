package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.Colors;
import com.isep.harrypotter.view.GUIParser;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import com.isep.harrypotter.view.scene.*;
import javafx.application.Platform;
import javafx.scene.control.MultipleSelectionModel;


public class Game {
    private final InputParser inputParser;
    private final OutputManager outputManager;
    private boolean isGameFinished;
    private final CharacterController characterController;
    private final ChapterController chapterController;
    private final SpellController spellController;
    private final PotionController potionController;

    private WelcomeView welcomeView;
    private GameView gameView;
    private SchoolView schoolView;
    private PotionClassView potionClassView;
    private SpellClassView spellClassView;

    public Game(InputParser inputParser, OutputManager outputManager) {
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.isGameFinished = false;
        this.spellController = new SpellController(inputParser, outputManager);
        this.potionController= new PotionController(inputParser, outputManager);
        this.characterController = new CharacterController(inputParser, outputManager, spellController, potionController);
        this.chapterController = new ChapterController(inputParser, outputManager, new Chapter(1));
        if (inputParser instanceof GUIParser){
            this.welcomeView = new WelcomeView();
            this.gameView = new GameView();
            this.schoolView = new SchoolView();
            this.potionClassView = new PotionClassView();
            this.spellClassView = new SpellClassView();
            eventListener();
        }
    }

    private void eventListener(){
        ((GUIParser) this.inputParser).addScene("welcome", welcomeView.getScene());
        ((GUIParser) this.inputParser).addScene("game", gameView.getScene());
        ((GUIParser) this.inputParser).addScene("school", schoolView.getScene());
        ((GUIParser) this.inputParser).addScene("potionClass", potionClassView.getScene());
        ((GUIParser) this.inputParser).addScene("spellClass", spellClassView.getScene());

        welcomeView.getButtonPlay().setOnAction(event -> {
            ((GUIParser) this.inputParser).changeScene("wizard");
            characterController.getWizard().setFirstname(welcomeView.getFirstNameField());
            characterController.getWizard().setLastname(welcomeView.getLastNameField());
            initGame();
        });
        welcomeView.getButtonQuit().setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        gameView.getButtonGoToSchool().setOnAction(event -> ((GUIParser) this.inputParser).changeScene("school"));
        gameView.getButtonSkipSchool().setOnAction(event -> isGameFinished = !characterController.skippingSchool());

        schoolView.getSpellClass().setOnAction(event -> {
            ((GUIParser) this.inputParser).changeScene("spellClass");
            spellClassGUI(characterController.getWizard());
        });

        schoolView.getPotionClass().setOnAction(event -> {
            ((GUIParser) this.inputParser).changeScene("potionClass");
            potionClassGUI();
        });

        potionClassView.getLearnButton().setOnAction(event -> {
            Potion potion = potionController.getAvailablePotionByName(potionClassView.getSelectedItem(), chapterController.getChapter().getNumber());
            if (null != potion){
                potionController.learnPotion(potion, characterController.getWizard());
                ((GUIParser) this.inputParser).changeScene("game");
            }
        });

        potionClassView.getBackButton().setOnAction(event -> {
            ((GUIParser) this.inputParser).changeScene("game");
        });

        spellClassView.getLearnButton().setOnAction(event -> {
            System.out.println(spellClassView.getSelectedItem());
            Spell spell = spellController.getAvailableSpellByName(spellClassView.getSelectedItem(), characterController.getWizard(), chapterController.getChapter().getNumber());
            if (null != spell) {
                spellController.learnSpell(spell, characterController.getWizard());
                ((GUIParser) this.inputParser).changeScene("game");
            }
        });

        spellClassView.getBackButton().setOnAction(event -> {
            ((GUIParser) this.inputParser).changeScene("game");
        });
    }

    public void wizardGUIUpdate(){
        gameView.setSpellList(characterController.getWizard().getKnownSpells());
        gameView.setPotionList(characterController.getWizard().getPotions());
        gameView.setInventoryList(characterController.getWizard().getInventory());
    }

    private void initGame(){
        characterController.initWizard();
        chapterController.initChapter();
    }

    public boolean gameChecker(){
        isGameFinished = chapterController.newDay();
        characterController.soberUp();

        if (this.characterController.getWizard().isNowPet()) {
            isGameFinished = petWizardGame();
            return false;
        }

        if (chapterController.isChapterFinish()) {
            outputManager.displayMessage(Colors.ERROR + "\nOh, what is happening ...? A BOSS ???\n\n" + Colors.ANSI_RESET + "You have to fight him with your known spells.\n" + Colors.WARNING +"You can see them with 'show spells' command. Boss can only be defeated with the good spell or object.\n" + Colors.ANSI_RESET, characterController.getWizard().getDrunk());

            //check if the wizard defeat the boss or not
            boolean victory = characterController.battleEnemy(chapterController.initBoss());

            //switch to the next chapter
            isGameFinished = chapterController.nextChapter(victory);
            return false;
        }
        return true;
    }

    private void gameLoop(){
        if (gameChecker()) {
            switch (displayMenu()) {
                case 1 -> goToSchool();

                //If the wizard skip school he can find potions, object, learn new spell and fight against an enemy depending on probabilities
                case 2 -> isGameFinished = !characterController.skippingSchool();
            }

            //display the progress bar of the chapter
            outputManager.progressPercentage(chapterController.getChapter().getDay(), 365, "day");
        }
    }

    public void play() {
        //initializations of the game
        initGame();

        //game loop
        while (!isGameFinished) {
            gameLoop();
        }

        //End of the game output
        outputManager.displayMessage("C'est fini sort de chez toi pour voir le dehors.", characterController.getWizard().getDrunk());
        System.exit(1);
    }


    private void goToSchool() {
        Wizard wizard = characterController.getWizard();
        outputManager.displayMessage("You can learn a spell or a potion, which book do you want to open?"  + Colors.WARNING +
                "\nYou can type " + Colors.SPELL + "'s'" + Colors.WARNING + " or " + Colors.SPELL + "'spell'" + Colors.WARNING + " to open spell book." +
                "\nYou can type" + Colors.POTION + " 'p' " + Colors.WARNING + " or " + Colors.POTION + "'potion' " + Colors.WARNING + " to open " + "potion book."  + Colors.ANSI_RESET, wizard.getDrunk());
        String choice = characterController.getString(wizard);

        //Choosing potion class
        if (choice.equals("potion") || choice.equals("p") || choice.equals("potions")) {
            potionClass(wizard);
        }

        //Choosing spell class
        else if (choice.equals("s") || choice.equals("spell") || choice.equals("spells")) {
            spellClass(wizard);
        }
        else {
            outputManager.displayMessage("You haven't find any book today... Let's go to sleep then....", wizard.getDrunk());
        }
    }

    private int displayMenu() {
        System.out.println("\nWhat a nice day, what are you going to do today ?" + Colors.WARNING +  " (1 or 2)" + Colors.ANSI_RESET);
        System.out.println("1. Go to school");
        System.out.println("2. Skipping school");
        return inputParser.getInt("Please enter an available proposition (1 or 2)");
    }

    private void potionClass(Wizard wizard) {
        this.outputManager.showListElements("All available potions are:", potionController.getAllAvailablePotions(chapterController.getChapter().getNumber()), wizard.getDrunk());
        this.outputManager.displayMessage("Enter the name of the potion you want to learn. " + Colors.WARNING + "(ex: Healing Salve) "+ Colors.ANSI_RESET, wizard.getDrunk());
        String input = characterController.getString(wizard);
        Potion potion = potionController.getAvailablePotionByName(input, chapterController.getChapter().getNumber());
        if (null != potion) {
            potionController.learnPotion(potion, wizard);
            this.outputManager.showMapElements("You have those potions:" + Colors.WARNING + " (ex: number* potionName)" +  Colors.ANSI_RESET, wizard.getPotions(), wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You learned useless things today", wizard.getDrunk());
        }
    }

    private void potionClassGUI(){
       potionClassView.setListView(potionController.getAllAvailablePotions(chapterController.getChapter().getNumber()));
    }

    private void spellClassGUI(Wizard wizard){
        spellClassView.setListView(spellController.getSpells(chapterController.getChapter().getNumber(), wizard));
    }

    private void spellClass(Wizard wizard) {
        this.outputManager.showMapElements("All available spells are: " + Colors.WARNING + "(you already know the underlined ones)", spellController.getSpells(chapterController.getChapter().getNumber(), wizard), wizard.getDrunk());
        this.outputManager.displayMessage("Enter the name of the spell you want to learn. " + Colors.WARNING + "(ex: Lumos)"+ Colors.ANSI_RESET, wizard.getDrunk());
        String input = characterController.getString(wizard);
        Spell spell = spellController.getAvailableSpellByName(input, wizard, chapterController.getChapter().getNumber());
        if (null != spell) {
            spellController.learnSpell(spell, wizard);
            this.outputManager.showListElements("You know those spells:", wizard.getKnownSpells(), wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You learned useless things today", wizard.getDrunk());
        }
    }

    private boolean petWizardGame() {
        outputManager.displayMessage("You have to find another potion to unpet yourself\nType the potion name", characterController.getWizard().getDrunk());
        String choice = characterController.getString(characterController.getWizard());
        Potion potion = potionController.getKnownPotionByName(choice, characterController.getWizard());
        if (null != potion) {
            outputManager.displayMessage("WOW yes !!! You retrieve your original body, let's continue the aventure",
                    characterController.getWizard().getDrunk());
            characterController.getWizard().setNowPet(false);
            return false;
        }
        else {
            outputManager.displayMessage("Sorry, you can't continue the aventure like that... let's stop here...",
                    characterController.getWizard().getDrunk());
            return true;
        }
    }
}
