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
        potions.add(new Potion("Polyjuice Potion", "Allows the drinker to assume the form of someone else", "Transformation", 100, 1, 3));
        potions.add(new Potion("Felix Felicis", "Brings good luck to the drinker", "Luck", 50, 2, 5));
        potions.add(new Potion("Veritaserum", "Forces the drinker to tell the truth", "Truth", 30, 0, 2));
        potions.add(new Potion("Draught of Living Death", "Puts the drinker into a deep sleep that mimics death", "Sleep", 10, 0, 4));
        potions.add(new Potion("Wolfsbane Potion", "Allows a werewolf to keep their human mind during the full moon", "Werewolf", 3, 1, 3));
        potions.add(new Potion("Amortentia", "The most powerful love potion in the world", "Love", 4, 1, 2));
    }

    public List<Potion> getAllPotions() {
        return potions;
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

    public Potion getAvailablePotionByName(String potionName) {
        for (Potion potion : potions) {
            if (potion.getName().equalsIgnoreCase(potionName)) {
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
