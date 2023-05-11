package com.isep.harrypotter.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SceneManager {
    private final Stage stage;
    private final Map<String, Scene> scenes;
    private final List<String> viewQueue;
    private int currentViewIndex;

    public SceneManager(Stage stage) {
        this.stage = stage;
        this.scenes = new HashMap<>();
        this.viewQueue = new ArrayList<>();
        this.currentViewIndex = -1;
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

        viewQueue.remove(sceneName);
        currentViewIndex = viewQueue.indexOf(sceneName);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/isep/harrypotter/assets/css/style.css")).toExternalForm());
        stage.setScene(scene);
    }

    public void addViewToQueue(String sceneName) {
        viewQueue.add(sceneName);
    }

    public void switchToNextScene() {
        if (viewQueue.isEmpty()) {
            throw new IllegalStateException("View queue is empty");
        }

        currentViewIndex++;
        if (currentViewIndex >= viewQueue.size()) {
            currentViewIndex = 0;
        }
        String currentView = viewQueue.get(currentViewIndex);
        viewQueue.remove(currentView);
        switchToScene(currentView);
    }



    public String getCurrentSceneName() {
        return viewQueue.get(currentViewIndex);
    }

    public String getNextSceneName() {
        int nextIndex = currentViewIndex + 1;
        if (nextIndex >= viewQueue.size()) {
            nextIndex = 0;
        }
        return viewQueue.get(nextIndex);
    }
}
