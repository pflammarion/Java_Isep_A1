package com.isep.harrypotter.view;

import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConsoleOutput implements OutputManager {
    public void displayMessage(String input, int drunkDays) {
        if (drunkDays > 0) {
            Random random = new Random();
            char[] characters = input.toCharArray();
            int rand = random.nextInt(input.length());
            for(int i = 0; i < rand; i++) {
                int randChar = random.nextInt(input.length());
                characters[randChar] = java.lang.Character.toUpperCase(characters[randChar]);
                input = new String(characters);
            }
        }
        System.out.println(input);
    }

    public void print(String input) {
        System.out.println(input);
    }

    public void showListElements(String introducer, List<?> list, int drunkDays) {
        displayMessage(introducer, drunkDays);
        for (Object element : list) {
            displayMessage("- " + Colors.MAP  + element.toString() + Colors.ANSI_RESET, drunkDays);
        }
    }

    public void showMapElements(String introducer, Map<?, ?> map, int drunkDays) {
        displayMessage(introducer, drunkDays);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Integer){
                displayMessage( entry.getValue() + "* " + Colors.MAP  + entry.getKey().toString()  + Colors.ANSI_RESET, drunkDays);
            }
            if (entry.getValue() instanceof Boolean){
                //for spell know printing
                if ((Boolean) entry.getValue()){
                    displayMessage( Colors.SPELL + Colors.ANSI_BOLD + Colors.ANSI_UNDERLINE + entry.getKey().toString()  + Colors.ANSI_RESET, drunkDays);
                } else {
                    displayMessage( Colors.SPELL + entry.getKey().toString()  + Colors.ANSI_RESET, drunkDays);
                }
            }
        }
    }


    public void readHelperFile() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/com/isep/harrypotter/assets/helper.txt");
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void progressPercentage(double current, int total, String choice) {
        switch (choice) {
            case "day"-> {
                print("\n\n" + Colors.DEFAULT + Colors.ANSI_WHITE_BACKGROUND + "New day :)" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BOLD + "\nYour year progress: \n" + Colors.ANSI_RESET);
            }
            case "fightWizard"-> System.out.println(Colors.WARNING + "\nYour life:\n" + Colors.ANSI_RESET);
            case "fightEnemy"-> System.out.println(Colors.WARNING + "\nEnemy's life:\n" + Colors.ANSI_RESET);
        }

        int percent = (int)(current / total * 100);
        int length = 25;
        int progress = (int)((double)length * current / total);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            if (i < progress) {
                sb.append(Colors.ANSI_GREEN_BACKGROUND + " " + Colors.ANSI_RESET);
            } else {
                sb.append(Colors.ANSI_RED_BACKGROUND + " " + Colors.ANSI_RESET);
            }
        }
        sb.append("]");
        if (percent > 50){
            sb.append(Colors.VALIDE);
        }
        else if (percent > 30){
            sb.append(Colors.WARNING);
        }
        else sb.append(Colors.ERROR);
        sb.append(String.format(" %d%%", percent));
        sb.append(Colors.ANSI_RESET);
        System.out.print(sb + "\r\n");
        if (current == total) {
            System.out.println();
        }
    }
}
