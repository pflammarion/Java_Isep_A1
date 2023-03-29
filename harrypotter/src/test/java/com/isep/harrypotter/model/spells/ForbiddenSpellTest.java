package com.isep.harrypotter.model.spells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForbiddenSpellTest {

    private ForbiddenSpell spell;

    @BeforeEach
    void setUp() {
        spell = new ForbiddenSpell("Avada Kedavra", "Causes instant death to the target", 100, 1000, "Instant death", 4);
    }

    @Test
    void getName() {
        assertEquals("Avada Kedavra", spell.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Causes instant death to the target", spell.getDescription());
    }

    @Test
    void getEnergyCost() {
        assertEquals(100, spell.getEnergyCost());
    }

    @Test
    void getDamage() {
        assertEquals(1000, spell.getDamage());
    }

    @Test
    void getMinimumChapter() {
        assertEquals(4, spell.getMinimumChapter());
    }

    @Test
    void setName() {
        spell.setName("Crucio");
        assertEquals("Crucio", spell.getName());
    }

    @Test
    void setDescription() {
        spell.setDescription("Causes unbearable pain to the target");
        assertEquals("Causes unbearable pain to the target", spell.getDescription());
    }

    @Test
    void setEnergyCost() {
        spell.setEnergyCost(50);
        assertEquals(50, spell.getEnergyCost());
    }

    @Test
    void setDamage() {
        spell.setDamage(500);
        assertEquals(500, spell.getDamage());
    }

    @Test
    void setMinimumChapter() {
        spell.setMinimumChapter(5);
        assertEquals(5, spell.getMinimumChapter());
    }

    @Test
    void getEffect() {
        assertEquals("Instant death", spell.getEffect());
    }

    @Test
    void setEffect() {
        spell.setEffect("Unbearable pain");
        assertEquals("Unbearable pain", spell.getEffect());
    }

}