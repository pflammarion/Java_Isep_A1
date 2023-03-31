package com.isep.harrypotter.view;

import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConsoleOutput implements OutputManager {
    private final String ANSI_YELLOW = "\u001B[33m";

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
        int index = 1;
        for (Object element : list) {
            displayMessage(Colors.ANSI_CYAN + index + ". " + element.toString() + Colors.ANSI_RESET, drunkDays);
            index++;
        }
    }

    public void showMapElements(String introducer, Map<?, Integer> map, int drunkDays) {
        displayMessage(introducer, drunkDays);
        for (Map.Entry<?, Integer> entry : map.entrySet()) {
            displayMessage(Colors.ANSI_CYAN + entry.getValue().toString() + "* " + entry.getKey().toString()  + Colors.ANSI_RESET,
                    drunkDays);
        }
    }


    public void readHelperFile() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/com/isep/harrypotter/helper.txt");
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
                System.out.println(Colors.ANSI_YELLOW + "\nYour year progress: \n" + Colors.ANSI_RESET);
            }
            case "fightWizard"-> System.out.println(Colors.ANSI_YELLOW + "\nYour life:\n" + Colors.ANSI_RESET);
            case "fightEnemy"-> System.out.println(Colors.ANSI_YELLOW + "\nEnemy's life:\n" + Colors.ANSI_RESET);
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
            sb.append(Colors.ANSI_GREEN);
        }
        else if (percent > 30){
            sb.append(Colors.ANSI_YELLOW);
        }
        else sb.append(Colors.ANSI_RED);
        sb.append(String.format(" %d%%", percent));
        sb.append(Colors.ANSI_RESET);
        System.out.print(sb + "\r\n");
        if (current == total) {
            System.out.println();
        }
    }
}
