package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Chapter;
import com.isep.harrypotter.model.characters.Boss;
import com.isep.harrypotter.view.Colors;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class ChapterController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private Chapter chapter;
    public void initChapter() {
        this.chapter.setDay(0);
    }
    public boolean newDay() {
        if (!chapter.isChapterInit()) {
            outputManager.print(chapter.getName());
            chapter.setChapterInit(true);
        }
        if (chapter.getNumber() > 8) {
            //finish game
            return true;
        }
        this.chapter.setDay(chapter.getDay() + new Random().nextInt(21) + 20);
        return false;
    }
    public boolean isChapterFinish() {
        return !chapter.isBossPassed() && this.chapter.getDay() > 365.25;
    }

    public boolean nextChapter(boolean victory) {
        if (victory) {
            this.chapter = new Chapter(this.chapter.getNumber() + 1);
            return false;
        }
        else {
            outputManager.print(Colors.ERROR + "END GAME" + Colors.ANSI_RESET);
            return true;
        }
    }
    public Boss initBoss() {
        return this.chapter.getBoss();
    }
}
