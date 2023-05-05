package com.isep.harrypotter.view.scene;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.Stuff;
import com.isep.harrypotter.model.spells.AbstractSpell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class GameView {

    private final Button buttonSkipSchool;
    private final Button buttonGoToSchool;
    private final ListView<String> spellList;
    private final ListView<String> potionList;
    private final ListView<String> inventoryList;
    private final Scene scene;
    public GameView() {

        // Create ImageView with Image
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/game_background.jpg"))));


        this.spellList = new ListView<>();
        spellList.setMaxSize(200, 200);
        this.potionList = new ListView<>();
        potionList.setMaxSize(200, 200);
        this.inventoryList = new ListView<>();
        inventoryList.setMaxSize(200, 200);

        HBox recapHBox = new HBox(spellList, potionList, inventoryList);
        recapHBox.setSpacing(10);
        recapHBox.setAlignment(Pos.CENTER);

        // Create Buttons
        buttonGoToSchool = new Button("Go to school");
        buttonGoToSchool.setAlignment(Pos.CENTER);
        buttonGoToSchool.setPrefSize(200, 50);

        buttonSkipSchool = new Button("Skip School");
        buttonSkipSchool.setAlignment(Pos.CENTER);
        buttonSkipSchool.setPrefSize(200, 50);

        // Create VBox to hold Buttons
        VBox vbox = new VBox(recapHBox, buttonGoToSchool, buttonSkipSchool);
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

    public void setSpellList(List<AbstractSpell> list){
        List<String> strings = list.stream()
                .map(AbstractSpell::toString)
                .toList();
        ObservableList<String> observableList = FXCollections.observableArrayList(strings);
        this.spellList.setItems(observableList);
        this.spellList.setSelectionModel(null);
        this.spellList.getStyleClass().add("spell-list");
    }

    public void setInventoryList(List<Stuff> list) {
        List<String> strings = list.stream()
                .map(Stuff::toString)
                .toList();
        ObservableList<String> observableList = FXCollections.observableArrayList(strings);
        this.inventoryList.setItems(observableList);
        this.inventoryList.setSelectionModel(null);
        this.inventoryList.getStyleClass().add("inventory-list");
    }

    public void setPotionList(Map<Potion, Integer> potionMap){
        List<String> strings = new ArrayList<>();
        for (Map.Entry<Potion, Integer> entry : potionMap.entrySet()) {
            Potion potion = entry.getKey();
            int number = entry.getValue();
            String spellString = number + "* " + potion.toString();
            strings.add(spellString);
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(strings);

        this.potionList.setItems(observableList);
        this.potionList.setSelectionModel(null);
        this.potionList.getStyleClass().add("potion-list");
    }
}
