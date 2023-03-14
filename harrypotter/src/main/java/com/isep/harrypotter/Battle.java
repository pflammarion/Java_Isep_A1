package com.isep.harrypotter;

import com.isep.harrypotter.characters.AbstractEnemy;
import com.isep.harrypotter.characters.Wizard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Battle {
    private Wizard wizard;
    private AbstractEnemy enemy;

}
