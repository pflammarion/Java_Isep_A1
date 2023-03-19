package com.isep.harrypotter.model;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Potion {
    private String name;
    private String description;
    private int price;
    private int minimumChapter;

    public Potion(String name, String description, int price, int requiredLevel){
        this.name = name;
        this.description = description;
        this.price = price;
        this.minimumChapter = requiredLevel;
    }
}
