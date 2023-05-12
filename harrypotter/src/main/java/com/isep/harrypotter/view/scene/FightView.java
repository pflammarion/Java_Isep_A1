package com.isep.harrypotter.view.scene;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.Stuff;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.Spell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class FightView {

    private final Scene scene;
    private final Label enemyName;
    private final Label wizardName;
    private final Button fightButton;
    private final ComboBox<String> objectSelector;
    private final ListView<String> listView;
    private final ImageView enemyImage;
    private final ImageView wizardImage;
    private final TextFlow fightInfo;
    private Map<Potion, Integer> potionList;
    private List<AbstractSpell> knownSpell;
    private List<Stuff> inventory;

    public FightView(){

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/fight.jpg"))));

        enemyImage = new ImageView();
        enemyImage.setFitHeight(250);
        enemyImage.setPreserveRatio(true);

        enemyName = new Label("enemy");

        VBox enemyVBox = new VBox(enemyName, enemyImage);
        enemyVBox.setSpacing(20);
        enemyVBox.setAlignment(Pos.CENTER);

        wizardImage = new ImageView();
        wizardImage.setFitHeight(250);
        wizardImage.setPreserveRatio(true);

        wizardName = new Label("wizard");

        VBox wizardVBox = new VBox(wizardName, wizardImage);
        wizardVBox.setSpacing(20);
        enemyVBox.setAlignment(Pos.CENTER);

        HBox characterHBox = new HBox(enemyVBox, wizardVBox);
        characterHBox.setSpacing(100);
        characterHBox.setAlignment(Pos.CENTER);

        fightInfo = new TextFlow();

        VBox rightPartVBox = new VBox(characterHBox, fightInfo);

        listView = new ListView<>();

        fightButton = new Button("Fight !");
        fightButton.setAlignment(Pos.CENTER);
        fightButton.setPrefSize(200, 50);

        objectSelector = new ComboBox<>();

        HBox buttonContainerHBox = new HBox(objectSelector, fightButton);
        buttonContainerHBox.setSpacing(20);
        buttonContainerHBox.setAlignment(Pos.CENTER);

        VBox leftPartVBox = new VBox(listView, buttonContainerHBox);
        leftPartVBox.setSpacing(50);
        leftPartVBox.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(rightPartVBox, leftPartVBox);
        hbox.setSpacing(200);
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

    public void updateWizardName(String firstname, String lastname){
        this.wizardName.setText(firstname + " " + lastname);
    }

    public void updateEnemyName(String name){
        this.enemyName.setText(name);
    }

    public void updateList(Object list) {
        List<String> strings = new ArrayList<>();

        if (list instanceof Map<?, ?>) {
            Map<?, Integer> map = (Map<?, Integer>) list;
            for (Map.Entry<?, Integer> entry : map.entrySet()) {
                Object object = entry.getKey();
                int number = entry.getValue();
                String spellString = number + "* " + object.toString();
                strings.add(spellString);
            }
        } else if (list instanceof List<?>) {
            List<?> itemList = (List<?>) list;
            strings = itemList.stream()
                    .map(Object::toString)
                    .toList();
        } else {
            throw new IllegalArgumentException("Invalid input type: " + list.getClass().getSimpleName());
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(strings);
        this.listView.setItems(observableList);
        this.listView.setSelectionModel(null);
        this.listView.getStyleClass().add("list");
    }

    public void setLists(Map<Potion,Integer> potions, List<AbstractSpell> spells,List<Stuff> stuffs) {
        this.potionList = potions;
        this.knownSpell= spells;
        this.inventory = stuffs;

        List<String> list = new ArrayList<>();

        if (knownSpell.size() > 0){
            list.add("Spells");
        }
        if (potionList.size() > 0){
            list.add("Potions");
        }
        if (inventory.size() > 0){
            list.add("Inventory");
        }

        objectSelector.getItems().addAll(list);
        if (!objectSelector.getItems().isEmpty()) {
            objectSelector.setValue(objectSelector.getItems().get(0));
        }
        updateList(knownSpell);
    }

    public ComboBox<String> getObjectSelector() {
        return objectSelector;
    }
}
