package com.isep.utils;


import com.isep.harrypotter.Potion;
import com.isep.harrypotter.SortingHat;
import com.isep.harrypotter.spells.Spell;
import com.isep.harrypotter.characters.Wizard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleParser implements InputParser {

    private Scanner scanner;

    public ConsoleParser()
    {
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu(){
        System.out.println("\nWhat a nice day, what are you going to do today ?");
        System.out.println("1. Go to school");
        System.out.println("2. Skipping school");
        return getInt("Please enter an available proposition");
    }
    public Wizard initWizard(){
        System.out.println("Enter your wizard firstname");
        String firstname = getString();
        System.out.println("Enter your wizard lastname");
        String lastname = getString();
        Wizard wizard = new Wizard(firstname, lastname, SortingHat.assignHouse());
        System.out.println("Hello " + firstname + " " + lastname);
        System.out.println("Welcome to Poudlard");
        System.out.println("Your pet is " + wizard.getPet() + " and were assigned to " + wizard.getHouse() + " house with your nice " + wizard.getWand().getCore() + " wand core");
        return wizard;
    }

    public Spell findSpellByName(Wizard wizard, boolean know){
        return Spell.loopInSpell(getString(), wizard, know);
    }

    public Object battleChoice(Wizard wizard){
        String input = getString();
        Spell spell = Spell.loopInSpell(input, wizard, true);
        Potion potion = Potion.loopInPotions(input, wizard);
        if (null != spell){
            return spell;
        }
        else if (null != potion){
            return potion;
        }
        else{
            return null;
        }
    }

    private int getInt(String messageWhenMismatch)
    {
        boolean validInput = false;
        int value = 0;

        // Looping until an integer is provided
        do {
            try
            {
                value = this.scanner.nextInt();
                // Consuming the \n char so it doesn't break the next call for nextLine()
                this.scanner.nextLine();
                validInput = true;
            }
            catch (InputMismatchException e)
            {
                //System.out.print("/!\\ Invalid value (not a number).\nPlease provide a number: ");
                System.out.printf("/!\\ Invalid value (not a number)%n%s: ", messageWhenMismatch);
                scanner.nextLine();
            }
        } while(!validInput);

        return value;
    }


    private String getString()
    {
        boolean validInput = false;
        String s = null;

        // Looping until a string is provided
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

}
