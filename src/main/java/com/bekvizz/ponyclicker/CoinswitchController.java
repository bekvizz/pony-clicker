package com.bekvizz.ponyclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CoinswitchController {
    @FXML
    private AnchorPane scenePane;
    private Image coinStill;
    private Image coinPressed;
    @FXML
    private Label clicksLabel;
    @FXML
    private ImageView defaultCoin;
    @FXML
    private ImageView luckyCoin;
    @FXML
    private ImageView cozyGlowCoin;
    @FXML
    private Label selectLabelDefault;
    @FXML
    private Label selectLabelLucky;
    @FXML
    private Label selectLabelCozyGlow;
    private String coinSkin = "default";
    private int countOfClicks;
    private Image cursorImage;

    public void setCursor(Image cursor) {
        this.scenePane.setCursor(new ImageCursor(cursor));
        this.cursorImage = cursor;
    }

    public void setCoinStill(Image coinStill) {
        this.coinStill = coinStill;
    }

    public void setCoinPressed(Image coinPressed) {
        this.coinPressed = coinPressed;
    }

    public void setClicksLabel(String clicks) {
        this.clicksLabel.setText(clicks);
    }

    public void setCountOfClicks(int countOfClicks) {
        this.countOfClicks = countOfClicks;
    }

    public void getDefaultInfo(MouseEvent mouseEvent) {
        coinSkin = "default";
    }

    public void getLuckyInfo(MouseEvent mouseEvent) {
        coinSkin = "lucky-roll";
    }

    public void getCozyGlowInfo(MouseEvent mouseEvent) {
        coinSkin = "cozy-glow";
    }

    public void switchCoinSkin() {
        if (coinSkin.equals("default")) {
            setStillAndPressed(coinSkin);
            selectLabelDefault.setText("Selected!");
            selectLabelLucky.setText("");
            selectLabelCozyGlow.setText("");
        } else if (coinSkin.equals("lucky-roll") && countOfClicks >= 100) {
            setStillAndPressed(coinSkin);
            selectLabelDefault.setText("");
            selectLabelLucky.setText("Selected!");
            selectLabelCozyGlow.setText("");
        } else if (coinSkin.equals("cozy-glow") && countOfClicks >= 500) {
            setStillAndPressed(coinSkin);
            selectLabelDefault.setText("");
            selectLabelLucky.setText("");
            selectLabelCozyGlow.setText("Selected!");
        } else if (coinSkin.equals("lucky-roll")) {
            selectLabelDefault.setText(selectLabelDefault.getText());
            selectLabelLucky.setText("Not enough!");
            selectLabelCozyGlow.setText(selectLabelCozyGlow.getText());
        } else if (coinSkin.equals("cozy-glow")) {
            selectLabelDefault.setText(selectLabelDefault.getText());
            selectLabelLucky.setText(selectLabelLucky.getText());
            selectLabelCozyGlow.setText("Not enough!");
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

    public void setStillAndPressed(String skin) {
        this.coinStill = new Image(getClass().getResourceAsStream("coins/" + skin + "/coin-click-0.png"));
        this.coinPressed = new Image(getClass().getResourceAsStream("coins/" + skin + "/coin-click-1.png"));
    }
}
