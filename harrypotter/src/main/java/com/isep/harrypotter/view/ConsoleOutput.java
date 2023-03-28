package com.isep.harrypotter.view;

import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConsoleOutput implements OutputManager{

    public void displayMessage(String input, int drunkDays){
        if (drunkDays > 0){
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

    public void print(String input){
        System.out.println(input);
    }

    public void showListElements(String introducer, List<?> list, int drunkDays) {
        displayMessage(introducer, drunkDays);
        int index = 1;
        for (Object element : list) {
            displayMessage(index + ". " + element.toString(), drunkDays);
            index++;
        }
    }

    public void showMapElements(String introducer, Map<?, Integer> map, int drunkDays) {
        displayMessage(introducer, drunkDays);
        for (Map.Entry<?, Integer> entry : map.entrySet()) {
            displayMessage(entry.getValue().toString() + "* " + entry.getKey().toString(), drunkDays);
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
        switch (choice){
            case "day"-> {
                spacer(5);
                System.out.println("\nYour year progress: \n");
            }
            case "fightWizard"->{
                System.out.println("\nYour life:\n");
            }
            case "fightEnemy"->{
                System.out.println("\nEnemy's life:\n");
            }
        }

        int percent = (int)(current / total * 100);
        int length = 25;
        int progress = (int)((double)length * current / total);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            if (i < progress) {
                sb.append("=");
            } else {
                sb.append(" ");
            }
        }
        sb.append("]");
        sb.append(String.format(" %d%%", percent));
        System.out.print(sb + "\r");
        if (current == total) {
            System.out.println();
        }
        if (choice.equals("days")){
            spacer(2);
        }
        else spacer(1);
    }

    private void spacer(int n){
        System.out.println("\n".repeat(Math.max(0, n)));
    }
}
