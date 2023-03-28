package com.isep.harrypotter.model.spells;


public class ForbiddenSpell extends AbstractSpell {
    private String effect;

    public ForbiddenSpell(String name, String description, int energyCost, int damage,  String effect, int minimumChapter) {
        super(name, description, energyCost, damage, minimumChapter);
        this.effect = effect;
    }
    @Override
    public String toString() {
        return getName();
    }

}
