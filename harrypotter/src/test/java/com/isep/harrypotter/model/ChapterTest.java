package com.isep.harrypotter.model;

import com.isep.harrypotter.model.characters.Boss;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChapterTest {

    private Chapter chapter;

    @BeforeEach
    void setUp() {
        chapter = new Chapter( 1);
    }

    @Test
    void getNumber() {
        assertEquals(1, chapter.getNumber());
    }

    @Test
    void isChapterInit() {
        assertFalse(chapter.isChapterInit());
    }

    @Test
    void isBossPassed() {
        assertFalse(chapter.isBossPassed());
    }

    @Test
    void getDay() {
        assertEquals(0, chapter.getDay());
    }

    @Test
    void getBoss() {
        assertNotNull(chapter.getBoss());
    }

    @Test
    void setNumber() {
        chapter.setNumber(2);
        assertEquals(2, chapter.getNumber());
    }

    @Test
    void setChapterInit() {
        chapter.setChapterInit(true);
        assertTrue(chapter.isChapterInit());
    }

    @Test
    void setBossPassed() {
        chapter.setBossPassed(true);
        assertTrue(chapter.isBossPassed());
    }

    @Test
    void setDay() {
        chapter.setDay(3);
        assertEquals(3, chapter.getDay());
    }
}