package com.isep.harrypotter.model;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Potion {
    private String name;
    private String description;
    private int price;
    private int duration;
    private int point;

    private String type;

    public Potion (String name, String description, int duration, String type, int point){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.type = type;
        this.point = point;
    }

    public String drinkPotion(Wizard wizard){
        String message = "You drunk the potion";
        int chance = 5;
        switch (this.type){
            case "health":
                double health = wizard.getCurrentHealth() + (this.point * wizard.getPotionEfficiency());
                int totalHealth = wizard.getTotalHealth();
                double heal = Math.min(health, totalHealth);
                if (wizard.randomProbability(10)){
                    wizard.setCurrentHealth(0);
                }
                else wizard.setCurrentHealth(heal);
                break;
            case "damage":
                //TODO damage
                break;
            case "precision":
                //TODO precision
                break;
            default:
                chance = 2;
        }

        if (wizard.randomProbability(chance)){
            message = "HUHOOO there was super alcohol in your super potion, gloups....";
            wizard.setDrunk(wizard.getDrunk() + 3);
        }
        if (wizard.randomProbability(chance * 3)){
            wizard.setNowPet(true);
            message = "Bahahhah you just became a pet lol and you are " + wizard.getPet();
        }
        message += "\nYour current health is : " + wizard.getCurrentHealth() + "/" + wizard.getTotalHealth();
        return message;
    }

    public static Potion loopInPotions(String potionName, Wizard wizard) {
        Potion potion = null;
        List<Potion> wizz = wizard.getPotions();
        for (Potion p : wizz) {
            if (p.getName().equalsIgnoreCase(potionName)) {
                potion = p;
                break;
            }
        }
        return potion;
    }
}
