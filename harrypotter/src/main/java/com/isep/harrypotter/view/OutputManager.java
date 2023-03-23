package com.isep.harrypotter.view;

import java.util.List;

public interface OutputManager {
    void displayMessage(String message, int drunkDays);
    void showListElements(String introducer, List<?> list, int drunkDays);
    void print(String input);
    void progressPercentage(double current, int total, String choice);
    void readHelperFile();
}
