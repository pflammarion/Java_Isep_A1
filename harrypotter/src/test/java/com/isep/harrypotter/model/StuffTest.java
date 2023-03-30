package com.isep.harrypotter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StuffTest {
    private Stuff stuff;

    @BeforeEach
    void setUp() {
        stuff = new Stuff("StuffName", "StuffDescription");
    }

    @Test
    void getName() {
        String expected = "StuffName";
        stuff.setName(expected);
        assertEquals(expected, stuff.getName());
    }

    @Test
    void getDescription() {
        String expected = "StuffDescription";
        stuff.setDescription(expected);
        assertEquals(expected, stuff.getDescription());
    }

    @Test
    void setName() {
        String expected = "NewName";
        stuff.setName(expected);
        assertEquals(expected, stuff.getName());
    }

    @Test
    void setDescription() {
        String expected = "NewDescription";
        stuff.setDescription(expected);
        assertEquals(expected, stuff.getDescription());
    }
}
