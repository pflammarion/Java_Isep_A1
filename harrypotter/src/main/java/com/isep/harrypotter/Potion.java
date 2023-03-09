package com.isep.harrypotter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    public void drinkPotion(Wizard wizard){
        int chance = 5;
        switch (this.type){
            case "health":
                int health = wizard.getCurrentHealth() + this.point;
                int totalHealth = wizard.getTotalHealth();
                int heal = Math.min(health, totalHealth);
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
            System.out.println("HUHOOO there was super alcohol in your super potion, gloups....");
            wizard.setDrunk(wizard.getDrunk() + 3);
        }
        if (wizard.randomProbability(chance * 3)){
            wizard.setNowPet(true);
            System.out.println("Bahahhah you just became a pet lol and you are " + wizard.getPet());
        }
    }
}
