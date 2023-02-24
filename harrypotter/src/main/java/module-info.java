module com.isep.harrypotter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.isep.harrypotter to javafx.fxml;
    exports com.isep.harrypotter;
}