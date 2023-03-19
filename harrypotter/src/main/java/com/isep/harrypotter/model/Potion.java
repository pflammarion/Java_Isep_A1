package com.isep.harrypotter.model;

import com.isep.harrypotter.model.characters.Wizard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Potion {
    private String name;
    private String description;
    private String type;
    private int point;
    private int price;
    private int minimumChapter;

    @Override
    public String toString() {
        return getName();
    }
}
