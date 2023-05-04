package com.isep.harrypotter.view;


import com.isep.harrypotter.controller.Game;
import com.isep.harrypotter.model.characters.Wizard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIParser extends Application implements InputParser
{
    private SceneManager sceneManager;
    @Override
    public void start(Stage stage) {
        this.sceneManager = new SceneManager(stage);
        Game game = new Game(this, new ConsoleOutput());
        this.sceneManager.switchToScene("welcome");
        stage.show();
        game.test();
    }

    public void launchInterface(String[] args)
    {
        launch(args);
    }

    public void changeScene(String sceneName) {
        this.sceneManager.switchToScene(sceneName);
    }

    public void addScene(String sceneName, Scene scene) {
        this.sceneManager.addScene(sceneName, scene);
    }


    //TODO faire pas de retour, ou revoir la structure pour pas que ça demande et que ça fasse tout d'un coup
    @Override
    public int getInt(String messageWhenMismatch) {
        /*if (button instanceof Button) {
            String buttonText = ((Button) button).getText();
            try {
                return Integer.parseInt(buttonText);
            } catch (NumberFormatException ex) {
                // handle the exception here, e.g. print an error message or throw a custom exception
                ex.printStackTrace();
            }
        }
        return null;*/
        return 0;
    }

    @Override
    public String getString(Wizard wizard) {
        return null;
    }

}
