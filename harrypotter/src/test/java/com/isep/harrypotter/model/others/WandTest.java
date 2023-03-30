package com.isep.harrypotter.model.others;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WandTest {
    Wand wand;

    @BeforeEach
    void setUp() {
        wand = new Wand(Core.PHOENIX_FEATHER, 14);
    }

    @Test
    void getCore() {
        assertEquals(Core.PHOENIX_FEATHER, wand.getCore());
    }

    @Test
    void getSize() {
        assertEquals(14, wand.getSize());
    }

    @Test
    void setCore() {
        wand.setCore(Core.PHOENIX_FEATHER);
        assertEquals(Core.PHOENIX_FEATHER, wand.getCore());
    }

    @Test
    void setSize() {
        wand.setSize(12);
        assertEquals(12, wand.getSize());
    }
}