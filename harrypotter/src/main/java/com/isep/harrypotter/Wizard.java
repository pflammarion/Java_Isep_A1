package com.isep.harrypotter;

import com.isep.utils.ConsoleOutput;
import com.isep.utils.OutputManager;
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

    public Wizard(String firstname, String lastname, House house) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.wand = new Wand(Core.values()[(int) (Math.random() * Core.values().length)], (int) (Math.random() * 50));
        this.pet = Pet.values()[new Random().nextInt(Pet.values().length)];
        this.house = house;
        this.isNowPet = false;
        this.drunk = 0;
    }
    public void defend(){

    }

    public boolean randomProbability(int chance){
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }
}
