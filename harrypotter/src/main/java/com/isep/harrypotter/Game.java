package com.isep.harrypotter;

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
        boolean isGameFinished = false;
        Wizard wizard = wizardInit();
        Scanner sc = new Scanner(System.in);

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
                System.out.println("lkjqnsdlkqldsjflqjsdflkjqlmdskfjlqmsjdfljqksfdmljlqksdf ?");
                String choice = sc.nextLine();
                //TODO jeu parallèle
            }
            else {
                switch (chapter.menu()){
                    case 1 -> wizard.learnSpell();
                    case 2 -> wizard.skippingSchool();
                }
            }
        }
    }

    private static Wizard wizardInit(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your wizard firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter your wizard lastname");
        String lastname = scanner.nextLine();
        Wizard wizard = new Wizard(firstname, lastname, SortingHat.assignHouse());
        System.out.println("Hello " + firstname + " " + lastname);
        System.out.println("Welcome to Poudlard");
        System.out.println("Your pet is " + wizard.getPet() + " and were assigned to " + wizard.getHouse() + " house with your nice " + wizard.getWand().getCore() + " wand core");
        return wizard;
    }
    public static void print(String input, Wizard wizard){
        if (wizard.getDrunk() > 0){
            Random random = new Random();
            char[] characters = input.toCharArray();
            int rand = random.nextInt(input.length());
            for(int i = 0; i < rand; i++){
                int randChar = random.nextInt(input.length());
                characters[randChar] = java.lang.Character.toUpperCase(characters[randChar]);
                input = new String(characters);
            }
            System.out.println(input);
        }
        else {
            System.out.println(input);
        }
    }
}
