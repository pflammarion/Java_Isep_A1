package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.ForbiddenSpell;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SpellController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private List<AbstractSpell> spells;

    public SpellController(InputParser inputParser, OutputManager outputManager){
        this.inputParser = inputParser;
        this.outputManager = outputManager;

        spells = new ArrayList<>();

        spells.add(new Spell("Wingardium Leviosa", "A charm that levitates objects", 50, 10, 1));
        spells.add(new Spell("Lumos", "A charm that creates light at the tip of the wand", 10, 0, 1));
        spells.add(new Spell("Alohomora", "A charm that unlocks and opens doors", 30, 0, 2));
        spells.add(new Spell("Expecto Patronum", "A charm that summons a guardian to protect against Dementors", 3, 10, 3));
        spells.add(new Spell("Expelliarmus", "A spell that disarms opponents by causing their wand to fly out of their hand", 4, 10, 4));
        spells.add(new Spell("Protego", "A charm that creates a shield to block spells and other attacks", 5, 5, 4));
        spells.add(new Spell("Accio", "A charm that summons an object to the caster", 20, 5, 2));
        spells.add(new Spell("Stupefy", "A stunning spell that knocks out opponents", 8, 15, 5));
        spells.add(new Spell("Impedimenta", "A spell that slows down or immobilizes opponents", 15, 10, 6));
        spells.add(new Spell("Reducto", "A spell that blasts solid objects into pieces", 25, 20, 6));
        spells.add(new Spell("Confundo", "A spell that confuses the target and causes them to act against their own will", 10, 5, 7));
        spells.add(new Spell("Serpensortia", "A spell that conjures a snake from the tip of the wand", 12, 10, 7));
        spells.add(new Spell("Incarcerous", "A spell that binds and restrains the target with ropes", 18, 15, 7));
        spells.add(new Spell("Silencio", "A spell that silences the target and prevents them from speaking or casting spells", 20, 10, 7));
        spells.add(new Spell("Diffindo", "A spell that cuts or tears objects apart", 22, 15, 7));
        spells.add(new Spell("Enervate", "A spell that revives a person who has been stunned or knocked out", 5, 5, 7));
        spells.add(new Spell("Muffliato", "A spell that creates a buzzing sound in the ears of anyone nearby to mask conversation", 15, 5, 7));
        spells.add(new Spell("Petrificus Totalus", "A spell that immobilizes the target completely", 30, 25, 7));
        spells.add(new Spell("Episkey", "A spell that heals minor injuries", 8, 5, 7));

        spells.add(new ForbiddenSpell("Cruciatus Curse", "A curse that causes intense pain", 100, 20, "Super effect", 6));
        spells.add(new ForbiddenSpell("Avada Kedavra", "A curse that causes instant death", 6, 10, "Instant death", 7));
        spells.add(new ForbiddenSpell("Imperio", "A curse that allows the caster to control the actions of the target", 70, 30, "Complete control", 7));
        spells.add(new ForbiddenSpell("Fiendfyre", "A cursed fire that is almost impossible to control and destroys everything in its path", 80, 40, "Unstoppable fire", 7));
        spells.add(new ForbiddenSpell("Blood Boiling Curse", "A curse that raises the body temperature of the target until their blood boils", 60, 25, "Boiling blood", 6));
        spells.add(new ForbiddenSpell("Cruciatus Majora", "A more powerful version of the Cruciatus Curse that inflicts even more intense pain", 120, 25, "Extreme pain", 7));
        spells.add(new ForbiddenSpell("Morsmordre", "A spell that conjures the Dark Mark, the sign of Voldemort's followers", 50, 5, "Dark Mark", 6));
        spells.add(new ForbiddenSpell("Sectumsempra", "A spell that creates wounds as if the target has been slashed by a sword", 40, 20, "Deep wounds", 6));
        spells.add(new ForbiddenSpell("Avada Kedavra Maxima", "An even more powerful version of the Avada Kedavra curse that causes an explosion upon impact", 80, 35, "Explosive death", 7));
        spells.add(new ForbiddenSpell("Horcrux Curse", "A spell that allows the caster to create a Horcrux, a powerful object that can grant immortality at a terrible cost", 150, 50, "Horcrux creation", 7));
    }

    public List<AbstractSpell> getAllSpells() {
        return spells;
    }

    public List<AbstractSpell> getAllKnownSpells(Wizard wizard) {
        List<AbstractSpell> knownSpells = new ArrayList<>();
        for (AbstractSpell spell : this.spells) {
            if (wizard.getKnownSpells().contains(spell)) {
                knownSpells.add(spell);
            }
        }
        return knownSpells;
    }


    public List<Spell> getKnownSpells(Wizard wizard) {
        List<Spell> knownSpells = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && wizard.getKnownSpells().contains(spell)) {
                knownSpells.add((Spell) spell);
            }
        }
        return knownSpells;
    }


    public List<ForbiddenSpell> getForbiddenSpells() {
        List<ForbiddenSpell> forbiddenSpells = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (spell instanceof ForbiddenSpell) {
                forbiddenSpells.add((ForbiddenSpell) spell);
            }
        }
        return forbiddenSpells;
    }

    public List<Spell> getSpells(int chapter) {
        List<Spell> spellList = new ArrayList<>();
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && chapter >= spell.getMinimumChapter()) {
                spellList.add((Spell) spell);
            }
        }
        return spellList;
    }

    public Spell getKnownSpellByName(String spellName, Wizard wizard) {
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && spell.getName().equalsIgnoreCase(spellName) && wizard.getKnownSpells().contains(spell)) {
                return (Spell) spell;
            }
        }
        return null;
    }

    public Spell getAvailableSpellByName(String spellName, Wizard wizard, int chapter) {
        for (AbstractSpell spell : spells) {
            if (spell instanceof Spell && spell.getName().equalsIgnoreCase(spellName) && !wizard.getKnownSpells().contains(spell) && spell.getMinimumChapter() <= chapter) {
                return (Spell) spell;
            }
        }
        return null;
    }
    public void learnSpell(AbstractSpell spell, Wizard wizard){
        List<AbstractSpell> knownSpells = getAllKnownSpells(wizard);
        knownSpells.add(spell);
        wizard.setKnownSpells(knownSpells);
        outputManager.displayMessage("You have learned the " + spell.getName() +" spell!\n", wizard.getDrunk());
    }

}
