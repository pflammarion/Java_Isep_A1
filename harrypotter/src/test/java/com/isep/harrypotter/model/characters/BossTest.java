package com.isep.harrypotter.model.characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BossTest {
    Boss boss;

    @BeforeEach
    void init(){
        boss = new Boss(200, 200, 1, 50, 0.1, "Ganon", null, "Special Spell");
        boss.setDamage(10);
        boss.setAccuracy(0.8);
        boss.setDefense(50);
    }

    @Test
    void getName() {
        assertEquals("Ganon", boss.getName());
    }

    @Test
    void setName() {
        boss.setName("Ganondorf");
        assertEquals("Ganondorf", boss.getName());
    }

    @Test
    void takeDamage() {
        boss.takeDamage(30);
        assertEquals(170, boss.getCurrentHealth());
    }

    @Test
    void getTotalHealth() {
        assertEquals(200, boss.getTotalHealth());
    }

    @Test
    void getCurrentHealth() {
        assertEquals(200, boss.getCurrentHealth());
    }

    @Test
    void getDefense() {
        assertEquals(50, boss.getDefense());
    }

    @Test
    void getDamage() {
        assertEquals(10, boss.getDamage());
    }

    @Test
    void getAccuracy() {
        assertEquals(0.8, boss.getAccuracy(), 0.0001);
    }

    @Test
    void setTotalHealth() {
        boss.setTotalHealth(250);
        assertEquals(250, boss.getTotalHealth());
    }

    @Test
    void setCurrentHealth() {
        boss.setCurrentHealth(150);
        assertEquals(150, boss.getCurrentHealth());
    }

    @Test
    void setDefense() {
        boss.setDefense(70);
        assertEquals(70, boss.getDefense());
    }

    @Test
    void setDamage() {
        boss.setDamage(60);
        assertEquals(60, boss.getDamage());
    }

    @Test
    void setAccuracy() {
        boss.setAccuracy(0.9);
        assertEquals(0.9, boss.getAccuracy(), 0.0001);
    }

    @Test
    void getSpecialObject() {
        assertNull(boss.getSpecialObject());
    }

    @Test
    void getSpecialSpell() {
        assertEquals("Special Spell", boss.getSpecialSpell());
    }

    @Test
    void setSpecialObject() {
        String specialObject = "Ball";
        boss.setSpecialObject(specialObject);
        assertEquals(specialObject, boss.getSpecialObject());
    }

    @Test
    void setSpecialSpell() {
        String specialSpell = "Accio";
        boss.setSpecialSpell(specialSpell);
        assertEquals(specialSpell, boss.getSpecialSpell());
    }
}