package com.isep.harrypotter.model.spells;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellTest {

    @Test
    void getName() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        assertEquals("Fireball", spell.getName());
    }

    @Test
    void getDescription() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        assertEquals("Launches a fiery ball at the target", spell.getDescription());
    }

    @Test
    void getEnergyCost() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        assertEquals(5, spell.getEnergyCost());
    }

    @Test
    void getDamage() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        assertEquals(10, spell.getDamage());
    }

    @Test
    void getMinimumChapter() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        assertEquals(1, spell.getMinimumChapter());
    }

    @Test
    void setName() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        spell.setName("Frostbolt");
        assertEquals("Frostbolt", spell.getName());
    }

    @Test
    void setDescription() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        spell.setDescription("Unleashes a freezing bolt at the target");
        assertEquals("Unleashes a freezing bolt at the target", spell.getDescription());
    }

    @Test
    void setEnergyCost() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        spell.setEnergyCost(3);
        assertEquals(3, spell.getEnergyCost());
    }

    @Test
    void setDamage() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        spell.setDamage(8);
        assertEquals(8, spell.getDamage());
    }

    @Test
    void setMinimumChapter() {
        Spell spell = new Spell("Fireball", "Launches a fiery ball at the target", 5, 10, 1);
        spell.setMinimumChapter(3);
        assertEquals(3, spell.getMinimumChapter());
    }
}