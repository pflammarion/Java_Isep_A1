package com.isep.utils;

import com.isep.harrypotter.Wizard;

import java.util.Random;

public class ConsoleOutput implements OutputManager{

    public void displayMessage(String input, Wizard wizard){
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
