package com.isep.harrypotter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        Scanner scanner = new Scanner(System.in);
        boolean isGameFinished = false;
        System.out.println("Enter your wizard firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter your wizard lastname");
        String lastname = scanner.nextLine();
        Wizard wizard = new Wizard(firstname, lastname, SortingHat.assignHouse());
        //wizard.setWand();
        //wizard.setHouse();
        System.out.println("Hello " + firstname + " " + lastname);
        System.out.println("Welcome to Poudlard");
        System.out.println("Your pet is " + wizard.getPet() + " and were assigned to " + wizard.getHouse() + " house with your nice " + wizard.getWand().getCore() + " wand core");

        while (!isGameFinished){
            if (!chapter.isChapterInit()){
                System.out.println(chapter.getName());
            }
            if (chapter.getNumber() > 8){
                isGameFinished = true;
            }
            System.out.println("What a nice day, what are you going to do today ?");
            String choice = scanner.nextLine();
        }
        /*
        knownSpells.add(new Spell("Wingardium Leviosa", 1));
        wizard.setKnownSpells(knownSpells);


        System.out.print("Enter the name of the spell to cast: ");
        String spellName = scanner.nextLine();
        wizard.castSpell(spellName);

        wizard.learnSpell();
        */
    }
}