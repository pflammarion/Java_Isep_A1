package com.isep.harrypotter.view;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ConsoleOutputTest {

    @Test
    void print() {
        ConsoleOutput output = new ConsoleOutput();
        String input = "Hello, world!";
        output.print(input); // Simply tests that the method doesn't throw an exception
    }

    @Test
    void showListElements() {
        ConsoleOutput output = new ConsoleOutput();
        String introducer = "Here are the elements:";
        List<String> list = Arrays.asList("a", "b", "c");
        int drunkDays = 0;
        output.showListElements(introducer, list, drunkDays); // Simply tests that the method doesn't throw an exception
    }

    @Test
    void showMapElements() {
        ConsoleOutput output = new ConsoleOutput();
        String introducer = "Here are the elements:";
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        int drunkDays = 0;
        output.showMapElements(introducer, map, drunkDays); // Simply tests that the method doesn't throw an exception
    }

    @Test
    void readHelperFile() {
        ConsoleOutput output = new ConsoleOutput();
        output.readHelperFile(); // Simply tests that the method doesn't throw an exception
    }

    @Test
    void progressPercentage() {
        ConsoleOutput output = new ConsoleOutput();
        double current = 50;
        int total = 100;
        String choice = "day";
        output.progressPercentage(current, total, choice); // Simply tests that the method doesn't throw an exception
    }
}
