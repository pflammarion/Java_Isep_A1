module com.isep.harrypotter {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.isep.harrypotter to javafx.fxml;
    exports com.isep.harrypotter;
    exports com.isep.harrypotter.model.characters;
    opens com.isep.harrypotter.model.characters to javafx.fxml;
    exports com.isep.harrypotter.model.spells;
    opens com.isep.harrypotter.model.spells to javafx.fxml;
    exports com.isep.harrypotter.model;
    opens com.isep.harrypotter.model to javafx.fxml;
    exports com.isep.harrypotter.controller;
    opens com.isep.harrypotter.controller to javafx.fxml;
    exports com.isep.harrypotter.model.others;
    opens com.isep.harrypotter.model.others to javafx.fxml;
    exports com.isep.harrypotter.view;
}