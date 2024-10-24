package com.bekvizz.ponyclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label percentage;
    @FXML
    private ImageView currentCoin;
    @FXML
    private Label doneLabel;
    @FXML
    private Label counterLabel;
    @FXML
    private ProgressBar progressBar;
    private int countOfClicks;
    private Image coinPressed;
    private Image coinStill;
    private Image cursorOnCoin = new Image(getClass().getResourceAsStream("cursors/Celestia-cursor.png"));
    private Image cursorImage;

    public void setCursor(Image cursor) {
        this.scenePane.setCursor(new ImageCursor(cursor));
        this.cursorImage = cursor;
    }

    public void setProgressBar(int countOfClicks) {
        this.progressBar.setProgress((double) countOfClicks / 1000);
        if (countOfClicks >= 1000) {
            doneLabel.setText("YAY! YOU'VE DONE!!!");
            percentage.setText(countOfClicks / 10 + "%");
        } else {
            percentage.setText(countOfClicks / 10 + "%");
        }
    }

    public void setCurrentCoin(Image currentCoinImage) {
        this.currentCoin.setImage(currentCoinImage);
    }

    public void setCoinPressed(Image coinPressed) {
        this.coinPressed = coinPressed;
    }

    public void setCoinStill(Image coinStill) {
        this.coinStill = coinStill;
    }

    public void setCountOfClicks(int countOfClicks) {
        this.countOfClicks = countOfClicks;
        this.counterLabel.setText(String.valueOf(countOfClicks));
    }

    public void countClicks() {
        countOfClicks++;
        counterLabel.setText(String.valueOf(countOfClicks));
        progressBar.setProgress((double) countOfClicks / 1000);

        if (countOfClicks >= 1000) {
            doneLabel.setText("YAY! YOU'VE DONE!!!");
            percentage.setText(countOfClicks / 10 + "%");
        } else {
            percentage.setText(countOfClicks / 10 + "%");
        }

    }

    public void coinAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            currentCoin.setImage(coinPressed);
        } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            currentCoin.setImage(coinStill);
        }
    }

    public void cursorOnCoin() {
        if (countOfClicks >= 1000) {
            cursorOnCoin = new Image(getClass().getResourceAsStream("cursors/Luna-cursor.png"));
            currentCoin.setCursor(new ImageCursor(cursorOnCoin));
        } else {
            currentCoin.setCursor(new ImageCursor(cursorOnCoin));
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();
        menuController.setCountOfClicks(countOfClicks);
        menuController.setYourClicksLabel(countOfClicks);
        menuController.setCoinPressed(coinPressed);
        menuController.setCoinStill(coinStill);
        menuController.setCursor(scenePane.getCursor());
        menuController.setCursorImage(cursorImage);
        menuController.setPlayButton("Return to game...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
