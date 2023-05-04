package com.isep.harrypotter.view.scene;

import com.isep.harrypotter.Main;
import com.isep.harrypotter.controller.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class WelcomeView {

    private final Button buttonQuit;
    private final Button buttonPlay;
    private final Scene scene;
    public WelcomeView() {

    // Create ImageView with Image
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/home-background.jpg"))));

        imageView.setFitHeight(468.0);
        imageView.setFitWidth(800.0);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);

    // Create Buttons
        buttonPlay = new Button("New Game");
        buttonPlay.setAlignment(Pos.CENTER);
        buttonPlay.setPrefSize(200, 50);

    buttonQuit = new Button("Quit");
        buttonQuit.setAlignment(Pos.CENTER);
        buttonQuit.setPrefSize(200, 50);

    // Create VBox to hold Buttons
    VBox vbox = new VBox(buttonPlay, buttonQuit);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

    // Create AnchorPane to hold ImageView and VBox
    AnchorPane anchorPane = new AnchorPane(imageView, vbox);
        anchorPane.setPrefSize(800, 468);
        AnchorPane.setBottomAnchor(vbox, 100.0);
        AnchorPane.setLeftAnchor(vbox, 300.0);
        AnchorPane.setRightAnchor(vbox, 300.0);

        this.scene = new Scene(anchorPane);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getButtonQuit() {
        return buttonQuit;
    }

    public Button getButtonPlay() {
        return buttonPlay;
    }
}
