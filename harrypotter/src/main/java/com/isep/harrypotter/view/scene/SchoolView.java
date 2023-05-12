package com.isep.harrypotter.view.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;


public class SchoolView {

    private final Button potionClass;
    private final Button spellClass;
    private final Scene scene;
    public SchoolView() {

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/class_bg.jpg"))));

        potionClass = new Button("Potion Class");
        potionClass.setAlignment(Pos.CENTER);
        potionClass.setPrefSize(200, 50);

        /*Image buttonPotionBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/button/potion_button.gif")));
        BackgroundImage backgroundImagePotion = new BackgroundImage(buttonPotionBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundPotion = new Background(backgroundImagePotion);
        potionClass.setBackground(backgroundPotion)*/

        spellClass = new Button("Spell Class");
        spellClass.setAlignment(Pos.CENTER);
        spellClass.setPrefSize(200, 50);



        HBox hbox = new HBox(spellClass, potionClass);
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);

        AnchorPane anchorPane = new AnchorPane(imageView, hbox);
        anchorPane.setPrefSize(1200, 600);
        AnchorPane.setTopAnchor(hbox, 100.0);
        AnchorPane.setBottomAnchor(hbox, 100.0);
        AnchorPane.setLeftAnchor(hbox, 300.0);
        AnchorPane.setRightAnchor(hbox, 300.0);


        this.scene = new Scene(anchorPane);

        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
    }

    public Scene getScene() {
        return scene;
    }

    public Button getPotionClass() {
        return potionClass;
    }

    public Button getSpellClass() {
        return spellClass;
    }
}
