package com.isep.harrypotter.model.characters;

import com.isep.harrypotter.model.*;
import com.isep.harrypotter.model.others.Core;
import com.isep.harrypotter.model.others.House;
import com.isep.harrypotter.model.others.Pet;
import com.isep.harrypotter.model.others.Wand;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.Spell;
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
    private List<AbstractSpell> knownSpells = new ArrayList<>();
    private List<Potion> potions =  new ArrayList<>();
    private String firstname;
    private String lastname;
    private double potionEfficiency;
    private boolean isNowPet;
    private int drunk;
    private Random random = new Random();

    public Wizard() {
        this.wand = new Wand(Core.values()[(int) (Math.random() * Core.values().length)], (int) (Math.random() * 50));
        this.pet = Pet.values()[new Random().nextInt(Pet.values().length)];
        this.isNowPet = false;
        this.drunk = 0;
        this.potionEfficiency = 1;
        //Default depends on house
        setDamage(1);
        setAccuracy(1);
        setDefense(1);
        setTotalHealth(100);
        setCurrentHealth(100);
    }

    public boolean randomProbability(int chance){
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }

}
