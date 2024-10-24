package com.bekvizz.ponyclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
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
        setCenterOnScreen(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public void setCenterOnScreen(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }
}