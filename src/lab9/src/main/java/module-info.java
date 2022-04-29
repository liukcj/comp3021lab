module lab9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab9 to javafx.fxml;
    exports lab9;
}