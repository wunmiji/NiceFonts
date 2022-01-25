package com.nicefonts.ui.fonts;

import com.nicefonts.other.CollectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BackgroundController implements Initializable {


    @FXML
    private Label quoteLabel;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CollectionClass cc = CollectionClass.getInstance();
        quoteLabel.setText(cc.fontMap.get("quote"));
        nameLabel.setText(cc.fontMap.get("name"));

        quoteLabel.setStyle("-fx-text-fill: -fx-secondary;-fx-font-family: '" + cc.fontMap.get("family")
                + "';-fx-font-size: " + 32 + "px;-fx-font-style:" + cc.fontMap.get("style")
                + ";-fx-font-weight:" + cc.fontMap.get("weight") + ";");
    }



}
