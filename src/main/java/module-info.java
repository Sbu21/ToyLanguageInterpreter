module com.example.lab14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens model.DataTransferObjects to javafx.base;
    opens gui to javafx.fxml;
    exports gui;
}