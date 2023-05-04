package com.isep.harrypotter;

import com.isep.harrypotter.controller.Game;
import com.isep.harrypotter.view.*;
import com.isep.harrypotter.view.scene.WelcomeView;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private WelcomeView view;

    public static void main(String[] args) throws IOException {
        InputParser parser;
        Scanner scanner = new Scanner(System.in);

        boolean c = false;
        int choice = 0;
        while(!c)
        {
            System.out.print("Please enter your choice : ");
            choice = scanner.nextInt();

            if(choice == 1 || choice == 2)
            {
                c = true;
            }
        }


        if(choice == 1)
        {
            parser = new ConsoleParser(System.in);
            Game game = new Game(parser, new ConsoleOutput());
            game.play();
        }
        else {
            parser = new GUIParser();

            ((GUIParser) parser).launchInterface(args);
        }


        //run the console game
        //
        //launch();

    }
}
