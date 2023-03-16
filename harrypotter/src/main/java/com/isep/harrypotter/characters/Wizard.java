package com.isep.harrypotter.characters;

import com.isep.harrypotter.*;
import com.isep.harrypotter.spells.Spell;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Wizard extends Character {
    private Pet pet;
    private Wand wand;
    private House house;
    private List<Spell> knownSpells = new ArrayList<>();
    private List<Potion> potions =  new ArrayList<>();
    private String firstname;
    private String lastname;
    private double potionEfficiency;
    private boolean isNowPet;
    private int drunk;
    private Random random = new Random();

    public Wizard(String firstname, String lastname, House house) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.wand = new Wand(Core.values()[(int) (Math.random() * Core.values().length)], (int) (Math.random() * 50));
        this.pet = Pet.values()[new Random().nextInt(Pet.values().length)];
        this.house = house;
        this.isNowPet = false;
        this.drunk = 0;
        this.potionEfficiency = 1;
        //TODO en fonction de la maison
        setDamage(1);
        setAccuracy(1);
        setDefence(1);
        switch (house){
            case HUFFLEPUFF ->{
                this.potionEfficiency = 10;
            }
            case SLYTHERIN -> {
                setDamage(10);
            }
            case GRYFFINDOR -> {
                setDefence(10);
            }
            case RAVENCLAW -> {
                setAccuracy(0.5);
            }
        }
        setTotalHealth(100);
        setCurrentHealth(100);

    }

    public boolean randomProbability(int chance){
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }

    public String takeTurn(AbstractEnemy enemy) {
        if (random.nextDouble() < enemy.getAccuracy()) {
            // boss attack succeeds
            double actualDamage = enemy.getDamage() - getDefence();
            if (actualDamage <= 0) {
                actualDamage = 0; // ensure at least 1 damage is dealt
            }
            takeDamage(actualDamage);
            return enemy.getName() + " attacks you for " + enemy.getDamage() + " damage! But you have " + getDefence();
        } else {
            // boss attack fails, deal linear damage
            double attack = enemy.getDamage() * enemy.getAccuracy() *  random.nextDouble();
            double actualDamage = Math.max(attack, enemy.getAccuracy());
            actualDamage = (double) Math.round(actualDamage * 100) / 100;
            takeDamage(actualDamage);
            return enemy.getName() + " misses, but deals " + actualDamage + " damage due to the backlash!";
        }
    }
}
