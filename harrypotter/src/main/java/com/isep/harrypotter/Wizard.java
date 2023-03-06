package com.isep.harrypotter;

import lombok.*;

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
    private List<Spell> knownSpells;
    private List<Potion> potions;
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


    public void castSpell(String spellName) {
        Spell spell = null;
        for (Spell s : this.knownSpells) {
            if (s.getName().equalsIgnoreCase(spellName)) {
                spell = s;
                break;
            }
        }
        if (spell == null) {
            System.out.println("You don't know the spell " + spellName + "!");
        } else {
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
        System.out.print("Enter the number of the spell you want to learn: ");
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= availableSpells.size()) {
            Spell chosenSpell = availableSpells.get(choice-1);
            List<Spell> knownSpells = getKnownSpells();
            knownSpells.add(chosenSpell);
            setKnownSpells(knownSpells);
            System.out.println("You have learned the " + chosenSpell.getName() + " spell!");
        } else {
            System.out.println("Invalid choice.");
        }
    }



}
