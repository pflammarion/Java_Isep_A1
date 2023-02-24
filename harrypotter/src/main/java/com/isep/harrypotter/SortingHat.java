package com.isep.harrypotter;

public class SortingHat {
    public static House assignHouse() {

        // Generate a random number between 0 and 3
        int randomNum = (int) (Math.random() * 4);

        // Assign a house based on the random number
        if (randomNum == 0) {
            return House.GRYFFINDOR;
        } else if (randomNum == 1) {
            return House.RAVENCLAW;
        } else if (randomNum == 2) {
            return House.SLYTHERIN;
        } else {
            return House.HUFFLEPUFF;
        }
    }
}
