package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PotionController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private List<Potion> potions;

    public PotionController(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;

        potions = new ArrayList<>();
        potions.add(new Potion("Elixir of Life", "Doubles the drinker's current health", "health", 0, 10, 2));
        potions.add(new Potion("Healing Salve", "Restores a moderate amount of the drinker's health", "health", 50, 2, 1));
        potions.add(new Potion("Rejuvenation Tonic", "Gradually restores the drinker's health over time", "health", 20, 3, 3));
        potions.add(new Potion("Berserker Brew", "Increases the drinker's damage output, but at the cost of reduced accuracy", "damage", 10, 4, 2));
        potions.add(new Potion("Venomous Draught", "Poisons the drinker's weapon, causing additional damage over time", "damage", 5, 3, 1));
        potions.add(new Potion("Thunderbolt Tincture", "Electrifies the drinker's weapon, causing additional lightning damage", "damage", 8, 6, 3));
        potions.add(new Potion("Eagle Eye Elixir", "Increases the drinker's accuracy and critical hit chance", "accuracy", 0.3, 5, 2));
        potions.add(new Potion("Liquid Light", "Illuminates the drinker's target, making it easier to hit", "accuracy", 0.2, 2, 1));
        potions.add(new Potion("Shadow Essence", "Grants the drinker temporary invisibility, making them harder to hit", "accuracy", 0.2, 7, 3));
    }

    public List<Potion> getAllAvailablePotions(int chapter) {
        List<Potion> availablePotions = new ArrayList<>();
        for (Potion potion : potions) {
            if (chapter >= potion.getMinimumChapter()) {
                availablePotions.add(potion);
            }
        }
        return availablePotions;
    }

    public List<Potion> getKnownPotions(Wizard wizard) {
        List<Potion> knownPotions = new ArrayList<>();
        for (Potion potion : potions) {
            if (wizard.getPotions().contains(potion)) {
                knownPotions.add(potion);
            }
        }
        return knownPotions;
    }

    public Potion getKnownPotionByName(String potionName, Wizard wizard) {
        for (Potion potion : potions) {
            if (potion.getName().equalsIgnoreCase(potionName) && wizard.getPotions().contains(potion)) {
                return potion;
            }
        }
        return null;
    }

    public Potion getAvailablePotionByName(String potionName, int chapter) {
        for (Potion potion : potions) {
            if (potion.getName().equalsIgnoreCase(potionName) && potion.getMinimumChapter() >= chapter) {
                return potion;
            }
        }
        return null;
    }

    public void learnPotion(Potion potion, Wizard wizard){
        if (null != potion){
            List<Potion> knownPotions = getKnownPotions(wizard);
            knownPotions.add(potion);
            wizard.setPotions(knownPotions);
            outputManager.displayMessage("You have learned the " + potion.getName() +" potion!\n", wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You loose.... idk what", wizard.getDrunk());
        }
    }
}
