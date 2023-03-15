package com.isep.utils;


import com.isep.harrypotter.Potion;
import com.isep.harrypotter.SortingHat;
import com.isep.harrypotter.spells.Spell;
import com.isep.harrypotter.characters.Wizard;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
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
        String firstname = getString(null);
        System.out.println("Enter your wizard lastname");
        String lastname = getString(null);
        Wizard wizard = new Wizard(firstname, lastname, SortingHat.assignHouse());
        System.out.println("Hello " + firstname + " " + lastname);
        System.out.println("Welcome to Poudlard");
        System.out.println("Your pet is " + wizard.getPet() + " and were assigned to " + wizard.getHouse() + " house with your nice " + wizard.getWand().getCore() + " wand core");
        return wizard;
    }

    public Spell findSpellByName(Wizard wizard, boolean know){
        return Spell.loopInSpell(getString(wizard), wizard, know);
    }

    public Object battleChoice(Wizard wizard){
        String input = getString(wizard);
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


    private String getString(Wizard wizard) {
        boolean validInput = false;
        String userInput = null;

        do {
            try {
                userInput = this.scanner.nextLine();
                if(userInput.startsWith("s") || userInput.startsWith("show")){
                    int firstSpaceIndex = userInput.indexOf(" ");
                    userInput = userInput.substring(firstSpaceIndex + 1);
                    switch (userInput) {
                        case "potions" -> displayPotions(wizard);
                        case "spells" -> displaySpells(wizard);
                        default -> System.out.println("Nothing to see there");
                    }
                }
                else validInput = true;

            } catch (InputMismatchException e) {
                System.out.print("/!\\ Invalid value (not a string).\nPlease provide a string: ");
                scanner.nextLine();
            }
        } while(!validInput);

        return userInput;
    }


    // TODO put it back into output parser
    private void displayPotions(Wizard wizard) {
        System.out.println("\nYou have those potions:");
        List<Potion> potions = wizard.getPotions();
        if(potions.size() == 0){
            System.out.println("You don't have have any potion");
        }
        else {
            for (int i = 0; i < potions.size(); i++) {
                System.out.println((i+1) + ". " + potions.get(i).getName());
            }

        }
        System.out.println("\nYou can continue the previous action\n\n");
    }

    private void displaySpells(Wizard wizard) {
        System.out.println("\nYou know those spells:");
        List<Spell> knownSpells = wizard.getKnownSpells();
        if(knownSpells.size() == 0){
            System.out.println("You don't know any spell");
        }
        else {
            for (int i = 0; i < knownSpells.size(); i++) {
                System.out.println((i+1) + ". " + knownSpells.get(i).getName());
            }
        }

        System.out.println("\nYou can continue the previous action\n\n");
    }


}
