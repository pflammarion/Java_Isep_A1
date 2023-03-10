package com.isep.harrypotter;

import com.isep.utils.ConsoleOutput;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class Wizard extends Character{
    private Pet pet;
    private Wand wand;
    private House house;
    private List<Spell> knownSpells = new ArrayList<>();
    private List<Potion> potions =  new ArrayList<>();
    private String firstname;
    private String lastname;
    private int totalHealth;
    private int currentHealth;
    private boolean isNowPet;
    private int drunk;
    private ConsoleOutput consoleOutput;

    public Wizard(String firstname, String lastname, House house) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.wand = new Wand(Core.values()[(int) (Math.random() * Core.values().length)], (int) (Math.random() * 50));
        this.pet = Pet.values()[new Random().nextInt(Pet.values().length)];
        this.house = house;
        this.isNowPet = false;
        this.drunk = 0;
        this.consoleOutput = new ConsoleOutput();
    }
    public void defend(){

    }

    public void castSpell(String spellName){
        Spell spell = Spell.loopInSpell(spellName, this, true);
        if (spell == null){
            this.consoleOutput.displayMessage("You don't know the spell " + spellName + "!", this);
        }else {
            this.consoleOutput.displayMessage("You cast the spell " + spell.getName(), this);
        }
    }

    public void learnSpell() {
        Scanner sc = new Scanner(System.in);
        List<Spell> availableSpells = Spell.getAvailableSpells(this);
        this.consoleOutput.displayMessage("Available spells:", this);
        for (int i = 0; i < availableSpells.size(); i++) {
            this.consoleOutput.displayMessage((i+1) + ". " + availableSpells.get(i).getName(), this);
        }
        this.consoleOutput.displayMessage("Enter the name of the spell you want to learn: ", this);
        String choice = sc.nextLine();
        Spell spell = Spell.loopInSpell(choice, this, false);
        if (spell != null) {
            this.knownSpells.add(spell);
            this.consoleOutput.displayMessage("You have learned the " + spell.getName() + " spell!", this);
            printKnownSpells();
        } else {
            this.consoleOutput.displayMessage("Invalid choice.", this);
        }
    }

    public void skippingSchool(){
        this.consoleOutput.displayMessage("You decided to skip school.", this);
        if (randomProbability(10)){
            this.consoleOutput.displayMessage("What a lucky day, you just found a new potion", this);
            this.potions.add(new Potion("super potion", "Potion to get hp", 3, "health", 30));
            printAvailablePotions();
        }
        if (randomProbability(10)){
            this.consoleOutput.displayMessage("What a lucky day, you just learned a new spell", this);
            this.knownSpells.add(new Spell("super forbidden spell", 20));
            printKnownSpells();
        }
    }

    public void printAvailablePotions(){
        this.consoleOutput.displayMessage("You have those potions:", this);
        for (int i = 0; i < this.potions.size(); i++) {
            this.consoleOutput.displayMessage((i+1) + ". " + potions.get(i).getName(), this);
        }
    }

    public void printKnownSpells(){
        this.consoleOutput.displayMessage("You know those spells:", this);
        for (int i = 0; i < this.knownSpells.size(); i++) {
            this.consoleOutput.displayMessage((i+1) + ". " + knownSpells.get(i).getName(), this);
        }
    }

    public boolean randomProbability(int chance){
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }
}
