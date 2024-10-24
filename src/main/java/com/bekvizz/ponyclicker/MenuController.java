package com.bekvizz.ponyclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private AnchorPane scenePane;
    private Parent root;
    @FXML
    private Stage stage;
    private Scene scene;
    private int countOfClicks;
    private Image coinPressed;
    private Image coinStill;
    @FXML
    private Label yourClicksLabel;
    @FXML
    private Button playButton;
    @FXML
    private Button exitButton;
    private StringBuilder secretCode = new StringBuilder();
    private Image cursorImage = new Image(getClass().getResourceAsStream("cursors/Twilight-cursor.png"));

    public void setCursorImage(Image cursorImage) {
        this.cursorImage = cursorImage;
    }

    public void setCursor(Cursor cursor) {
        this.scenePane.setCursor(cursor);
    }

    public void setPlayButton(String text) {
        this.playButton.setText(text);
    }

    public void setCoinPressed(Image coinPressed) {
        this.coinPressed = coinPressed;
    }

    public void setCoinStill(Image coinStill) {
        this.coinStill = coinStill;
    }

    public void setCountOfClicks(int countOfClicks) {
        this.countOfClicks = countOfClicks;
    }

    public void setYourClicksLabel(int clicks) {
        this.yourClicksLabel.setText("Your clicks: " + clicks);
        this.countOfClicks = clicks;
    }

    public void switchToGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        GameController gameController = loader.getController();
        gameController.setCountOfClicks(countOfClicks);
        gameController.setCursor(cursorImage);
        gameController.setProgressBar(countOfClicks);
        if (coinPressed == null) {
            gameController.setCoinPressed(new Image(getClass().getResourceAsStream("coins/default/coin-click-1.png")));
            gameController.setCoinStill(new Image(getClass().getResourceAsStream("coins/default/coin-click-0.png")));
            gameController.setCurrentCoin(new Image(getClass().getResourceAsStream("coins/default/coin-click-0.png")));
        } else {
            gameController.setCoinPressed(coinPressed);
            gameController.setCoinStill(coinStill);
            gameController.setCurrentCoin(coinStill);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCoinswitch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("coinswitch.fxml"));
        root = loader.load();
        CoinswitchController coinswitchController = loader.getController();
        coinswitchController.setCountOfClicks(countOfClicks);
        coinswitchController.setClicksLabel("Your clicks: " + countOfClicks);
        coinswitchController.setCoinStill(coinStill);
        coinswitchController.setCoinPressed(coinPressed);
        coinswitchController.setCursor(cursorImage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to leave!");
        alert.setContentText("Are you sure you want to exit?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

    public void secretClicks(KeyEvent event) {
        secretCode.append(event.getCharacter());
        if (secretCode.toString().equals("m")
                || secretCode.toString().equals("ma")
                || secretCode.toString().equals("mag")
                || secretCode.toString().equals("magi")
                || secretCode.toString().equals("magic")) {
            if (secretCode.toString().equals("magic")) {
                countOfClicks += 500;
                Image img = new Image(getClass().getResourceAsStream("cursors/Chrysalis-cursor.png"));
                scenePane.setCursor(new ImageCursor(img));
                cursorImage = img;
                yourClicksLabel.setText(" 0_o Hmmm... Your clicks: " + countOfClicks);
                secretCode.delete(0, secretCode.length());
            }
        } else {
            secretCode.delete(0, secretCode.length());
        }
    }
}