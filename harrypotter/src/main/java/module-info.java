module com.isep.harrypotter {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.isep.harrypotter to javafx.fxml;
    exports com.isep.harrypotter;
}