module com.bekvizz.ponyclicker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bekvizz.ponyclicker to javafx.fxml;
    exports com.bekvizz.ponyclicker;
}