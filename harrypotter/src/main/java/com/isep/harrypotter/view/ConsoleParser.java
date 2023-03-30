package com.isep.harrypotter.view;


import com.isep.harrypotter.model.characters.Wizard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleParser implements InputParser {

    private final Scanner scanner;

    public ConsoleParser() {
        this.scanner = new Scanner(System.in);
    }

    public int getInt(String messageWhenMismatch) {
        boolean validInput = false;
        int value = 0;

        do {
            try {
                value = this.scanner.nextInt();
                this.scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.printf("/!\\ Invalid value (not a number)%n%s: ", messageWhenMismatch);
                scanner.nextLine();
            }
        } while(!validInput);

        return value;
    }


    public String getString(Wizard wizard) {
        boolean validInput = false;
        String userInput = null;
        do {
            try {
                userInput = this.scanner.nextLine();
                validInput = true;

            } catch (InputMismatchException e) {
                System.out.print("/!\\ Invalid value (not a string).\nPlease provide a string: ");
                scanner.nextLine();
            }
        } while(!validInput);

        return userInput;
    }
}
