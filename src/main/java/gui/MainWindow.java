package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(MainWindow.class.getResource("/main.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 1280, 720);
        MainWindowController mainWindowController = fxmlLoader1.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader(MainWindow.class.getResource("/select.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1280, 720);
        SelectController selectController = fxmlLoader2.getController();

        mainWindowController.setSelectController(selectController);

        Stage stage2 = new Stage();
        stage2.setTitle("Select Window");
        stage2.setScene(scene2);
        stage2.show();

        stage.setTitle("Toy Language");
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}