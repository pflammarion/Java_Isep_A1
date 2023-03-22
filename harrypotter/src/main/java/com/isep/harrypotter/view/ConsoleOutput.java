package com.isep.harrypotter.view;

import java.io.IOException;
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

    public void progressPercentage(int current, int total) {
        spacer();
        System.out.println("\nYour year progress: \n");
        int percent = (int)((double)current / total * 100);
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
        spacer();
    }

    private void spacer(){
        System.out.println("\n\n\n\n\n\n");
    }
}
