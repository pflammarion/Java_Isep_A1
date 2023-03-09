package com.isep.harrypotter;

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

    public Wizard(String firstname, String lastname, House house) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.wand = new Wand(Core.values()[(int) (Math.random() * Core.values().length)], (int) (Math.random() * 50));
        this.pet = Pet.values()[new Random().nextInt(Pet.values().length)];
        this.house = house;
    }
    public void defend(){

    }

    public void castSpell(String spellName){
        Spell spell = Spell.loopInSpell(spellName, this, true);
        if (spell == null){
            System.out.println("You don't know the spell " + spellName + "!");
        }else {
            System.out.println("You cast the spell " + spell.getName());
        }
    }

    public void learnSpell() {
        List<Spell> availableSpells = Spell.getAvailableSpells(this);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available spells:");
        for (int i = 0; i < availableSpells.size(); i++) {
            System.out.println((i+1) + ". " + availableSpells.get(i).getName());
        }
        System.out.print("Enter the name of the spell you want to learn: ");
        String choice = scanner.nextLine();
        Spell spell = Spell.loopInSpell(choice, this, false);
        if (spell != null) {
            this.knownSpells.add(spell);
            System.out.println("You have learned the " + spell.getName() + " spell!");
            printKnownSpells();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void skippingSchool(){
        System.out.println("You decided to skip school.");
        Random random = new Random();
        int random1 = random.nextInt(10);
        int random2 = random.nextInt(10);
        if (random1 == random2){
            System.out.println("What a lucky day, you just found a new potion");
            this.potions.add(new Potion("super potion", 20));
            printAvailablePotions();
        }
    }

    public void printAvailablePotions(){
        System.out.println("You have those potions:");
        for (int i = 0; i < this.potions.size(); i++) {
            System.out.println((i+1) + ". " + potions.get(i).getName());
        }
    }

    public void printKnownSpells(){
        System.out.println("You know those spells:");
        for (int i = 0; i < this.knownSpells.size(); i++) {
            System.out.println((i+1) + ". " + knownSpells.get(i).getName());
        }
    }

    public boolean randomProbability(int chance){
        Random random = new Random();
        int random1 = random.nextInt(chance/10);
        int random2 = random.nextInt(chance/10);
        return random1 == random2;
    }
}
