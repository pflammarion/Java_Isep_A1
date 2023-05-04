package com.isep.harrypotter.view;


import com.isep.harrypotter.controller.Game;
import com.isep.harrypotter.model.characters.Wizard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIParser extends Application implements InputParser
{
    private SceneManager sceneManager;

    private Game game;
    @Override
    public void start(Stage stage) {
        this.sceneManager = new SceneManager(stage);
        this.game = new Game(this, new ConsoleOutput());
        this.sceneManager.switchToScene("welcome");
        stage.show();
    }

    public void launchInterface(String[] args)
    {
        launch(args);
    }

    public void changeScene(String sceneName) {
        if (sceneName.equalsIgnoreCase("game")){
            this.game.gameChecker();
        }
        this.sceneManager.switchToScene(sceneName);
    }

    public String getSceneName(){
        return this.sceneManager.getCurrentSceneName();
    }

    public void addScene(String sceneName, Scene scene) {
        this.sceneManager.addScene(sceneName, scene);
    }


    //TODO faire pas de retour, ou revoir la structure pour pas que ça demande et que ça fasse tout d'un coup
    @Override
    public int getInt(String messageWhenMismatch) {
        return 0;
    }

    @Override
    public String getString(Wizard wizard) {
        return null;
    }

}
