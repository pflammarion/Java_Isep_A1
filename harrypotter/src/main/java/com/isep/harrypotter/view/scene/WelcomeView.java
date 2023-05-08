package com.isep.harrypotter.view.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class WelcomeView {

    private final Button buttonQuit;
    private final Button buttonPlay;
    private final TextField firstNameField;
    private final TextField lastNameField;
    private final Scene scene;
    public WelcomeView() {

    // Create ImageView with Image
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/home-background.jpg"))));


        // Create labels and text fields
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.getStyleClass().add("white-text");
        firstNameField = new TextField();
        firstNameField.setMaxWidth(200);

        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.getStyleClass().add("white-text");
        lastNameField = new TextField();
        lastNameField.setMaxWidth(200);

        // Create VBox to hold labels and text fields
        VBox tfVBox = new VBox(10, firstNameLabel, firstNameField, lastNameLabel, lastNameField);
        tfVBox.setAlignment(Pos.CENTER_LEFT);
        tfVBox.setPadding(new Insets(20));

        HBox tfHBox = new HBox(tfVBox);
        tfHBox.setAlignment(Pos.CENTER);

    // Create Buttons
        buttonPlay = new Button("Start Game");
        buttonPlay.setAlignment(Pos.CENTER);
        buttonPlay.setPrefSize(200, 50);

    buttonQuit = new Button("Quit");
        buttonQuit.setAlignment(Pos.CENTER);
        buttonQuit.setPrefSize(200, 50);

    // Create VBox to hold Buttons
    VBox vbox = new VBox(tfHBox, buttonPlay, buttonQuit);
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

    public Button getButtonQuit() {
        return buttonQuit;
    }

    public Button getButtonPlay() {
        return buttonPlay;
    }

    public String getFirstNameField() {
        return firstNameField.getText();
    }
    public String getLastNameField() {
        return lastNameField.getText();
    }
}
