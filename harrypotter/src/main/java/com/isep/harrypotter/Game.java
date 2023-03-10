package com.isep.harrypotter;

import com.isep.utils.ConsoleOutput;
import com.isep.utils.ConsoleParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        Chapter chapter = new Chapter();
        ConsoleParser consoleParser = new ConsoleParser();
        ConsoleOutput consoleOutput = new ConsoleOutput();
        boolean isGameFinished = false;
        Wizard wizard = consoleParser.initWizard();

        while (!isGameFinished){
            if (!chapter.isChapterInit()){
                System.out.println(chapter.getName());
                chapter.setChapterInit(true);
            }
            if (chapter.getNumber() > 8){
                isGameFinished = true;
            }
            if (wizard.getDrunk() > 0){
               wizard.setDrunk(wizard.getDrunk() - 1);
            }
            if (wizard.isNowPet()){
                consoleOutput.displayMessage("lkjqnsdlkqldsjflqjsdflkjqlmdskfjlqmsjdfljqksfdmljlqksdf ?", wizard);

                //TODO jeu parallèle
            }
            else {
                switch (consoleParser.displayMenu()){
                    case 1 -> wizard.learnSpell();
                    case 2 -> wizard.skippingSchool();
                }
            }
        }
    }
}
