package com.isep.harrypotter.view.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class WizardView {

    private final Button buttonValidate;

    private final Label wizardFirstname;
    private final Label wizardLastname;
    private final Label house;
    private final Label wand;
    private final Label pet;
    private final ImageView wizardImageView;
    private final ImageView petImageView;
    private final Scene scene;
    public WizardView() {

        wizardFirstname = new Label();
        wizardLastname = new Label();
        house = new Label();
        wand = new Label();
        pet = new Label();

        buttonValidate = new Button("Ok");
        buttonValidate.setAlignment(Pos.CENTER);
        buttonValidate.setPrefSize(200, 50);

        // Create ImageView for wizard

        wizardImageView = new ImageView();
        wizardImageView.setFitHeight(250);
        wizardImageView.setPreserveRatio(true);

        // Create ImageView for wand
        ImageView wandImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/wand.gif"))));
        wandImageView.setFitHeight(100);
        wandImageView.setFitWidth(100);

        // Create ImageView for pet
        petImageView = new ImageView();
        petImageView.setFitHeight(100);
        petImageView.setFitWidth(100);


        HBox wizardHBox = new HBox(wizardFirstname, wizardLastname);
        wizardHBox.setAlignment(Pos.CENTER);
        wizardHBox.setSpacing(10);

        // Create HBox to hold wizard image and name labels
        VBox wizardVBox = new VBox(house, wizardImageView, wizardHBox);
        wizardVBox.setAlignment(Pos.CENTER);
        wizardVBox.setSpacing(20);

        // Create VBox to hold wand image and name label
        HBox wandHBox = new HBox(wandImageView, wand);
        wandHBox.setAlignment(Pos.CENTER_LEFT);
        wandHBox.setSpacing(20);

        // Create VBox to hold pet image and name label
        HBox petHBox = new HBox(petImageView, pet);
        petHBox.setAlignment(Pos.CENTER_LEFT);
        petHBox.setSpacing(20);

        VBox caracteristicVBox = new VBox(wandHBox, petHBox);
        caracteristicVBox.setAlignment(Pos.CENTER_LEFT);
        caracteristicVBox.setSpacing(50);

        // Create HBox to hold wandVBox and petVBox
        HBox centerHBox = new HBox(wizardVBox, caracteristicVBox);
        centerHBox.setSpacing(200);
        centerHBox.setAlignment(Pos.CENTER);

        // Create VBox to hold all the UI elements
        VBox centerVBox = new VBox(centerHBox, buttonValidate);
        centerVBox.setAlignment(Pos.CENTER);
        centerVBox.setSpacing(50);


        // Create AnchorPane to hold ImageView and VBox
        AnchorPane anchorPane = new AnchorPane(centerVBox);
        anchorPane.setPrefSize(1200, 600);
        AnchorPane.setTopAnchor(centerVBox, 50.0);
        AnchorPane.setBottomAnchor(centerVBox, 50.0);
        AnchorPane.setLeftAnchor(centerVBox, 100.0);
        AnchorPane.setRightAnchor(centerVBox, 100.0);
        anchorPane.getStyleClass().add("grey-bg");

        this.scene = new Scene(anchorPane);
    }

    public Scene getScene() {
        return scene;
    }


    public Button getButtonValidate() {
        return buttonValidate;
    }

    public void updateWizardFirstname(String firstname){
        this.wizardFirstname.setText(firstname);
    }

    public void updateWizardLastname(String lastname){
        this.wizardLastname.setText(lastname);
    }

    public void updateWand(String wand){
        this.wand.setText(wand);
    }

    public void updateHouse(String house){
        this.house.setText(house);
        Image wizardImage;
        if (house.equalsIgnoreCase("Gryffindor")) {
            wizardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/wizard/harry.gif")));
        } else if (house.equalsIgnoreCase("Slytherin")) {
            wizardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/wizard/sly.gif")));
        } else if (house.equalsIgnoreCase("Hufflepuff")) {
            wizardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/wizard/default.gif")));
        } else if (house.equalsIgnoreCase("Ravenclaw")) {
            wizardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/wizard/blue.gif")));
        } else {
            wizardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/wizard/default.gif")));
        }
        wizardImageView.setImage(wizardImage);
    }

    public void updatePet(String pet){
        this.pet.setText(pet);
        Image petImage;
        if (pet.equalsIgnoreCase("cat")) {
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/pet/cat.gif")));
        } else if (pet.equalsIgnoreCase("owl")) {
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/pet/owl.gif")));
        } else if (pet.equalsIgnoreCase("rat")) {
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/pet/rat.gif")));
        } else if (pet.equalsIgnoreCase("toad")) {
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/pet/toad.gif")));
        } else {
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/pet/default.gif")));
        }
        petImageView.setImage(petImage);
    }

}

