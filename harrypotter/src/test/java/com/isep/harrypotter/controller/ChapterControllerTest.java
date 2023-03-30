package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.view.ConsoleOutput;
import com.isep.harrypotter.view.ConsoleParser;
import com.isep.harrypotter.view.InputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChapterControllerTest {
    private ChapterController controller;
    @BeforeEach
    void setUp() {
        controller = new ChapterController(new ConsoleParser(), new ConsoleOutput(), new Chapter(1));
    }

    @Test
    void initChapter() {
        controller.initChapter();
        assertEquals(0, controller.getChapter().getDay());
    }

    @Test
    void newDay() {
        int previousDay = controller.getChapter().getDay();
        assertFalse(controller.newDay());
        int currentDay = controller.getChapter().getDay();
        assertTrue(currentDay > previousDay);
        assertTrue(controller.getChapter().isChapterInit());
    }

    @Test
    void isChapterFinish() {
        controller.getChapter().setBossPassed(false);
        controller.getChapter().setDay(0);
        assertFalse(controller.isChapterFinish());

        controller.getChapter().setBossPassed(false);
        controller.getChapter().setDay(365);
        assertFalse(controller.isChapterFinish());

        controller.getChapter().setBossPassed(true);
        controller.getChapter().setDay(0);
        assertFalse(controller.isChapterFinish());

        controller.getChapter().setBossPassed(true);
        controller.getChapter().setDay(365);
        assertFalse(controller.isChapterFinish());

        controller.getChapter().setBossPassed(false);
        controller.getChapter().setDay(366);
        assertTrue(controller.isChapterFinish());
    }

    @Test
    void nextChapter() {
        boolean isFinished = controller.nextChapter(true);
        assertFalse(isFinished);
        assertEquals(2, controller.getChapter().getNumber());

        isFinished = controller.nextChapter(false);
        assertTrue(isFinished);
    }

    @Test
    void getInputParser() {
        assertNotNull(controller.getInputParser());
    }

    @Test
    void getOutputManager() {
        assertNotNull(controller.getOutputManager());
    }

    @Test
    void getChapter() {
        assertNotNull(controller.getChapter());
    }

    @Test
    void setChapter() {
        Chapter newChapter = new Chapter(2);
        controller.setChapter(newChapter);
        assertEquals(newChapter, controller.getChapter());
    }
}