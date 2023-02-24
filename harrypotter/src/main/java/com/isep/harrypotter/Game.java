package com.isep.harrypotter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
    }
}