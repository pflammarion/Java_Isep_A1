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
        List<Spell> knownSpells = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
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

        System.out.println("  _______ _            _____  _     _ _                       _                      _____ _                   \n" +
                " |__   __| |          |  __ \\| |   (_) |                     | |                    / ____| |                  \n" +
                "    | |  | |__   ___  | |__) | |__  _| | ___  ___  ___  _ __ | |__   ___ _ __ ___  | (___ | |_ ___  _ __   ___ \n" +
                "    | |  | '_ \\ / _ \\ |  ___/| '_ \\| | |/ _ \\/ __|/ _ \\| '_ \\| '_ \\ / _ \\ '__/ __|  \\___ \\| __/ _ \\| '_ \\ / _ \\\n" +
                "    | |  | | | |  __/ | |    | | | | | | (_) \\__ \\ (_) | |_) | | | |  __/ |  \\__ \\  ____) | || (_) | | | |  __/\n" +
                "    |_|  |_| |_|\\___| |_|    |_| |_|_|_|\\___/|___/\\___/| .__/|_| |_|\\___|_|  |___/ |_____/ \\__\\___/|_| |_|\\___|\n" +
                "                                                       | |                                                     \n" +
                "                                                       |_|                                                     ");


        knownSpells.add(new Spell("Wingardium Leviosa", 1));
        wizard.setKnownSpells(knownSpells);

        System.out.print("Enter the name of the spell to cast: ");
        String spellName = scanner.nextLine();
        wizard.castSpell(spellName);

        wizard.learnSpell();
    }




}