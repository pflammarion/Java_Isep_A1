package com.isep.harrypotter.view.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class GameView {

    private final Button buttonSkipSchool;
    private final Button buttonGoToSchool;
    private final Scene scene;
    public GameView() {

        // Create ImageView with Image
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/game_background.jpg"))));

        // Create Buttons
        buttonGoToSchool = new Button("Go to school");
        buttonGoToSchool.setAlignment(Pos.CENTER);
        buttonGoToSchool.setPrefSize(200, 50);

        buttonSkipSchool = new Button("Skip School");
        buttonSkipSchool.setAlignment(Pos.CENTER);
        buttonSkipSchool.setPrefSize(200, 50);

        // Create VBox to hold Buttons
        VBox vbox = new VBox(buttonGoToSchool, buttonSkipSchool);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        // Create AnchorPane to hold ImageView and VBox
        AnchorPane anchorPane = new AnchorPane(imageView, vbox);
        anchorPane.setPrefSize(1200, 600);
        AnchorPane.setBottomAnchor(vbox, 100.0);
        AnchorPane.setLeftAnchor(vbox, 300.0);
        AnchorPane.setRightAnchor(vbox, 300.0);


        this.scene = new Scene(anchorPane);

        // Resize the image to fit the background without cropping
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
    }

    public Scene getScene() {
        return scene;
    }

    public Button getButtonGoToSchool() {
        return buttonGoToSchool;
    }

    public Button getButtonSkipSchool() {
        return buttonSkipSchool;
    }
}
