package com.isep.harrypotter.view.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class WizardView {

    private final Button buttonValidate;

    private final Label wizardFirstname;
    private final Label wizardLastname;
    private final Label house;
    private final Label wand;
    private final Label pet;
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


        // Create VBox to hold Buttons
        VBox vbox = new VBox(wizardFirstname, wizardLastname, house, wand, pet, buttonValidate);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        // Create AnchorPane to hold ImageView and VBox
        AnchorPane anchorPane = new AnchorPane(vbox);
        anchorPane.setPrefSize(800, 468);
        AnchorPane.setBottomAnchor(vbox, 100.0);
        AnchorPane.setLeftAnchor(vbox, 300.0);
        AnchorPane.setRightAnchor(vbox, 300.0);
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
    }

    public void updatePet(String pet){
        this.pet.setText(pet);
    }

}

