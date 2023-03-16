module com.isep.harrypotter {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.isep.harrypotter to javafx.fxml;
    exports com.isep.harrypotter;
    exports com.isep.harrypotter.characters;
    opens com.isep.harrypotter.characters to javafx.fxml;
    exports com.isep.harrypotter.spells;
    opens com.isep.harrypotter.spells to javafx.fxml;
}