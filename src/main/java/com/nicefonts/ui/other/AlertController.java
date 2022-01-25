package com.nicefonts.ui.other;

import com.nicefonts.other.CollectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertController implements Initializable {

    @FXML
    private Label alertLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertLabel.setText(CollectionClass.getInstance().fontMap.get("alert"));
    }
}