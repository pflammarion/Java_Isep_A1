package com.isep.harrypotter.model;

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
    private String type;
    private double point;
    private int price;
    private int minimumChapter;

    @Override
    public String toString() {
        return getName();
    }
}
