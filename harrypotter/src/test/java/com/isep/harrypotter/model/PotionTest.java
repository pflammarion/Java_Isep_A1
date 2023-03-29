package com.isep.harrypotter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    Potion potion;

    @BeforeEach
    void setUp() {
        potion = new Potion("Test Potion", "This is a test potion", "health", 10, 100, 5);
    }

    @Test
    void getName() {
        assertEquals("Test Potion", potion.getName());
    }

    @Test
    void getDescription() {
        assertEquals("This is a test potion", potion.getDescription());
    }

    @Test
    void getType() {
        assertEquals("health", potion.getType());
    }

    @Test
    void getPoint() {
        assertEquals(10, potion.getPoint());
    }

    @Test
    void getPrice() {
        assertEquals(100, potion.getPrice());
    }

    @Test
    void getMinimumChapter() {
        assertEquals(5, potion.getMinimumChapter());
    }

    @Test
    void setName() {
        potion.setName("New Test Potion");
        assertEquals("New Test Potion", potion.getName());
    }

    @Test
    void setDescription() {
        potion.setDescription("This is a new test potion");
        assertEquals("This is a new test potion", potion.getDescription());
    }

    @Test
    void setType() {
        potion.setType("mana");
        assertEquals("mana", potion.getType());
    }

    @Test
    void setPoint() {
        potion.setPoint(20);
        assertEquals(20, potion.getPoint());
    }

    @Test
    void setPrice() {
        potion.setPrice(200);
        assertEquals(200, potion.getPrice());
    }

    @Test
    void setMinimumChapter() {
        potion.setMinimumChapter(10);
        assertEquals(10, potion.getMinimumChapter());
    }
}
