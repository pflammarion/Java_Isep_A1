package com.isep.harrypotter;

public class SortingHat {
    public static House assignHouse() {

        // Generate a random number between 0 and 3
        int randomNum = (int) (Math.random() * 4);

        // Assign a house based on the random number
        if (randomNum == 0) {
            return new House("Gryffindor");
        } else if (randomNum == 1) {
            return new House("Hufflepuff");
        } else if (randomNum == 2) {
            return new House("Ravenclaw");
        } else {
            return new House("Slytherin");
        }
    }
}
