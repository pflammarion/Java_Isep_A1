package com.isep.harrypotter;

import java.util.List;
import java.util.Random;

public class Wizard extends Character{
    private Pet pet;
    private Wand wand;
    private House house;
    private List<Spell> knownSpells;
    private List<Potion> potions;
    private String firstname;
    private String lastname;

    public Wizard(String firstname, String lastname, House house) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.wand = new Wand(Core.values()[(int) (Math.random() * Core.values().length)], (int) (Math.random() * 50));
        this.pet = Pet.values()[new Random().nextInt(Pet.values().length)];
        this.house = house;
    }
    public void defend(){

    }

    public House getHouse() {
        return house;
    }
    public void setHouse(House house) {
        this.house = house;
    }

    public void setPet(Pet pet){
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }

    public List<Spell> getKnownSpells() {
        return knownSpells;
    }

    public void setKnownSpells(List<Spell> knownSpells) {
        this.knownSpells = knownSpells;
    }

    public void setWand(Wand wand) {
        this.wand = wand;
    }

    public Wand getWand() {
        return wand;
    }
}
