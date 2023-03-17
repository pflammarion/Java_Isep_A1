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

    public static Potion getPotionByName(String potionName, Wizard wizard) {
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
