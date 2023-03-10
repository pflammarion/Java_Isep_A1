package com.isep.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleParser {

    private Scanner scanner;

    public ConsoleParser()
    {
        this.scanner = new Scanner(System.in);
    }
    private String getString()
    {
        boolean validInput = false;
        String s = null;

        do {
            try
            {
                s = this.scanner.nextLine();
                validInput = true;
            }
            catch (InputMismatchException e)
            {
                System.out.print("/!\\ Invalid value (not a string).\nPlease provide a string: ");
                scanner.nextLine();
            }
        } while(!validInput);

        return s;
    }

    private int getInt(String messageWhenMismatch)
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

}
