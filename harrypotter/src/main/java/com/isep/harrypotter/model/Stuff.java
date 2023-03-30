package com.isep.harrypotter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stuff {
    private String name;
    private String description;
    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }
}
