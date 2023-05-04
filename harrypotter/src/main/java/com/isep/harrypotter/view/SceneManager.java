package com.isep.harrypotter.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneManager
{
    private final Stage stage;
    private final Map<String, Scene> scenes;
    private String currentSceneName;

    public SceneManager(Stage stage) {
        this.stage = stage;
        this.scenes = new HashMap<>();
        stage.setTitle("Harry Potter Game");
    }

    public void addScene(String sceneName, Scene scene) {
        scenes.put(sceneName, scene);
    }

    public void switchToScene(String sceneName) {
        Scene scene = scenes.get(sceneName);

        if (scene == null) {
            throw new IllegalArgumentException("Scene not found: " + sceneName);
        }

        currentSceneName = sceneName;
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/isep/harrypotter/assets/css/style.css")).toExternalForm());
        stage.setScene(scene);
    }


    public String getCurrentSceneName() {
        return currentSceneName;
    }

}
