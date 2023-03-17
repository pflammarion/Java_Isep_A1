package com.isep.harrypotter.view;


import com.isep.harrypotter.model.characters.Wizard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleParser implements InputParser {

    private Scanner scanner;

    public ConsoleParser()
    {
        this.scanner = new Scanner(System.in);
    }

    public int getInt(String messageWhenMismatch)
    {
        boolean validInput = false;
        int value = 0;

        do {
            try
            {
                value = this.scanner.nextInt();
                this.scanner.nextLine();
                validInput = true;
            }
            catch (InputMismatchException e)
            {
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
                int firstSpaceIndex = userInput.indexOf(" ");
                if (firstSpaceIndex != -1) {
                    String firstWord = userInput.substring(0, firstSpaceIndex);
                    if((userInput.startsWith("s") && firstWord.length() == 1) || userInput.startsWith("show")){
                        userInput = userInput.substring(firstSpaceIndex + 1);
                        switch (userInput) {
                            //TODO reimplement it
                            /*
                            case "potions" -> wizardController.displayPotions(wizard);
                            case "spells" -> wizardController.displaySpells(wizard);
                            default -> System.out.println("Nothing to see there");

                             */
                        }
                    }
                    else validInput = true;
                }
                else validInput = true;

            } catch (InputMismatchException e) {
                System.out.print("/!\\ Invalid value (not a string).\nPlease provide a string: ");
                scanner.nextLine();
            }
        } while(!validInput);

        return userInput;
    }

}
