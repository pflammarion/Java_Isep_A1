package com.isep.harrypotter.view.scene;

import com.isep.harrypotter.model.spells.Spell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class SpellClassView {

    private final ListView<String> listView;
    private final Button learnButton;
    private final Button backButton;
    private final Scene scene;
    public SpellClassView() {

        // Create ImageView with Image
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/isep/harrypotter/assets/img/background/class_bg.jpg"))));


        this.listView = new ListView<>();

        learnButton = new Button("Learn !");
        learnButton.setAlignment(Pos.CENTER);
        learnButton.setPrefSize(200, 50);

        backButton = new Button("Leave...");
        backButton.setAlignment(Pos.CENTER);
        backButton.setPrefSize(200, 50);

        VBox vBox = new VBox(learnButton, backButton);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        // Create VBox to hold Buttons
        HBox hbox = new HBox(listView, vBox);
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);

        // Create AnchorPane to hold ImageView and VBox
        AnchorPane anchorPane = new AnchorPane(imageView, hbox);
        anchorPane.setPrefSize(1200, 600);
        AnchorPane.setTopAnchor(hbox, 100.0);
        AnchorPane.setBottomAnchor(hbox, 100.0);
        AnchorPane.setLeftAnchor(hbox, 300.0);
        AnchorPane.setRightAnchor(hbox, 300.0);


        this.scene = new Scene(anchorPane);

        // Resize the image to fit the background without cropping
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
    }

    public Scene getScene() {
        return scene;
    }


    public void setListView(Map<Spell, Boolean> spellMap) {
        List<String> strings = new ArrayList<>();
        for (Map.Entry<Spell, Boolean> entry : spellMap.entrySet()) {
            Spell spell = entry.getKey();
            boolean isKnown = entry.getValue();
            String spellString = spell.toString() + (isKnown ? " (Known)" : "");
            strings.add(spellString);
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(strings);
        this.listView.setItems(observableList);

        // Set the cell factory to customize the appearance of the items
        this.listView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setDisable(true);
                } else {
                    setText(item);

                    // Set the text color based on whether the spell is known or not
                    if (item.endsWith("(Known)")) {
                        setStyle("-fx-text-fill: green;");
                        setDisable(true);
                    } else {
                        setStyle("");
                        setDisable(false);
                    }
                }
            }
        });
    }

    public Button getLearnButton() {
        return learnButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public String getSelectedItem() {
        // Get the selection model
        MultipleSelectionModel<String> selectionModel = this.listView.getSelectionModel();

        // Get the selected item
        return selectionModel.getSelectedItem();
    }
}
