package com.isep.harrypotter;

import com.isep.harrypotter.controller.Game;
import com.isep.harrypotter.view.ConsoleOutput;
import com.isep.harrypotter.view.ConsoleParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //set up console parser and output manager for the MVC structure as views
        Game game = new Game(new ConsoleParser(), new ConsoleOutput());

        //run the console game
        game.play();
        //launch();
    }

}
