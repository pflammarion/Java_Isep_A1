package com.isep.harrypotter.model.others;


import com.isep.harrypotter.model.others.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Wand {
    private Core core;
    private int size;

}
