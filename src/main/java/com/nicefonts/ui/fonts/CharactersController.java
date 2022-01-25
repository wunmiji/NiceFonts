package com.nicefonts.ui.fonts;

import com.nicefonts.other.CollectionClass;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CharactersController implements Initializable {

    private CollectionClass cc;

    @FXML
    private FlowPane upperCaseFlowPane;
    @FXML
    private FlowPane lowerCaseFlowPane;
    @FXML
    private FlowPane numbersFlowPane;
    @FXML
    private FlowPane symbolFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cc = CollectionClass.getInstance();

        Arrays.stream(cc.alphabetArray).forEach(s -> {
            upperCaseFlowPane.getChildren().add(eachFonts(s.toUpperCase()));
            lowerCaseFlowPane.getChildren().add(eachFonts(s.toLowerCase()));
        });

        Platform.runLater(() -> {
            Arrays.stream(cc.numberArray).forEach(s -> numbersFlowPane.getChildren().add(eachFonts(s)));
            Arrays.stream(cc.symbolArray).forEach(s -> symbolFlowPane.getChildren().add(eachFonts(s)));
        });
    }

    private Label eachFonts (String name) {
        Label label = new Label(name);
        label.setPrefSize(50, 50);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: #39559F;-fx-text-fill: -fx-secondary;-fx-font-family: '" + cc.fontMap.get("family") + "';" +
                "-fx-font-size: " + 25 + "px;-fx-font-style: " + cc.fontMap.get("style") + ";-fx-font-weight:" + cc.fontMap.get("weight") + ";");
        return label;
    }


}
