package com.isep.harrypotter.view.scene;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.Spell;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class FindObjectView {
    private final Button buttonValidate;
    private final Label objectLabel;
    private final ImageView objectImageView;
    private final Scene scene;
    public FindObjectView(){

        ImageView imageBackground= new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/garden.jpg"))));

        objectLabel = new Label();
        objectLabel.getStyleClass().add("object-title");

        buttonValidate = new Button("Ok");
        buttonValidate.setAlignment(Pos.CENTER);
        buttonValidate.setPrefSize(200, 50);

        // Create ImageView for wizard

        objectImageView = new ImageView();
        objectImageView.setFitHeight(250);
        objectImageView.setPreserveRatio(true);


        VBox objectVBox = new VBox(objectLabel, objectImageView);
        objectVBox.setAlignment(Pos.CENTER);
        objectVBox.setSpacing(20);


        // Create VBox to hold all the UI elements
        VBox centerVBox = new VBox(objectVBox, buttonValidate);
        centerVBox.setAlignment(Pos.CENTER);
        centerVBox.setSpacing(50);


        // Create AnchorPane to hold ImageView and VBox
        AnchorPane anchorPane = new AnchorPane(imageBackground, centerVBox);
        anchorPane.setPrefSize(1200, 600);
        AnchorPane.setTopAnchor(centerVBox, 50.0);
        AnchorPane.setBottomAnchor(centerVBox, 50.0);
        AnchorPane.setLeftAnchor(centerVBox, 100.0);
        AnchorPane.setRightAnchor(centerVBox, 100.0);

        this.scene = new Scene(anchorPane);

        // Resize the image to fit the background without cropping
        imageBackground.fitWidthProperty().bind(scene.widthProperty());
        imageBackground.fitHeightProperty().bind(scene.heightProperty());
    }

    public Scene getScene() {
        return scene;
    }

    public void setObject(Object object){
        Image objectImage;
        if (object instanceof AbstractSpell){
            this.objectLabel.setText(((AbstractSpell) object).getName());
            objectLabel.getStyleClass().add("spell-text");
            objectImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/object/spell_book.png")));

        } else if (object instanceof Potion) {
            this.objectLabel.setText(((Potion) object).getName());
            objectLabel.getStyleClass().add("potion-text");
            objectImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/object/potions.png")));
        }
        else {
            this.objectLabel.setText("Fireworks");
            objectLabel.getStyleClass().add("inventory-text");
            objectImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/object/fireworks.png")));
        }

        objectImageView.setImage(objectImage);
    }

    public Button getButtonValidate() {
        return buttonValidate;
    }
}
