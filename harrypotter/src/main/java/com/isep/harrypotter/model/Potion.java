package com.isep.harrypotter.model;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Potion {
    private String name;
    private String description;
    private int price;
    private int duration;
    private int point;
    private String type;
    @Override
    public String toString() {
        return this.name;
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
