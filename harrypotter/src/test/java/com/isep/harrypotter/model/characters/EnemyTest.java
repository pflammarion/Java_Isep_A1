package com.isep.harrypotter.model.characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    private Enemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new Enemy(100, 100, 100, 50, 10, "Goblin");
        enemy.setDamage(10);
        enemy.setAccuracy(90);
        enemy.setDefense(50);
    }

    @Test
    void getName() {
        assertEquals("Goblin", enemy.getName());
    }

    @Test
    void setName() {
        enemy.setName("Orc");
        assertEquals("Orc", enemy.getName());
    }

    @Test
    void takeDamage() {
        int damage = 20;
        double expectedCurrentHealth = enemy.getCurrentHealth() - damage;
        enemy.takeDamage(damage);
        assertEquals(expectedCurrentHealth, enemy.getCurrentHealth());
    }

    @Test
    void getTotalHealth() {
        assertEquals(100, enemy.getTotalHealth());
    }

    @Test
    void getCurrentHealth() {
        assertEquals(100, enemy.getCurrentHealth());
    }

    @Test
    void getDefense() {
        assertEquals(50, enemy.getDefense());
    }

    @Test
    void getDamage() {
        assertEquals(10, enemy.getDamage());
    }

    @Test
    void getAccuracy() {
        assertEquals(90, enemy.getAccuracy());
    }

    @Test
    void setTotalHealth() {
        enemy.setTotalHealth(200);
        assertEquals(200, enemy.getTotalHealth());
    }

    @Test
    void setCurrentHealth() {
        enemy.setCurrentHealth(50);
        assertEquals(50, enemy.getCurrentHealth());
    }

    @Test
    void setDefense() {
        enemy.setDefense(60);
        assertEquals(60, enemy.getDefense());
    }

    @Test
    void setDamage() {
        enemy.setDamage(20);
        assertEquals(20, enemy.getDamage());
    }

    @Test
    void setAccuracy() {
        enemy.setAccuracy(80);
        assertEquals(80, enemy.getAccuracy());
    }
}