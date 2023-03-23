package com.isep.harrypotter.view;

import java.io.*;
import java.util.List;
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
}
