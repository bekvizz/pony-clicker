package com.bekvizz.ponyclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Pony Clicker");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        scene.setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("cursors/Twilight-cursor.png"))));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}