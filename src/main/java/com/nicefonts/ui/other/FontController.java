package com.nicefonts.ui.other;

import com.nicefonts.controls.FavouriteCC;
import com.nicefonts.other.CollectionClass;
import com.nicefonts.pojo.json.settings.FontClass;
import com.nicefonts.other.TransitionClass;
import com.nicefonts.settings.FontSettings;
import com.nicefonts.stage.SceneClass;
import com.nicefonts.ui.main.NiceFontsController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FontController implements Initializable {

    private CollectionClass cc;

    private boolean clicked;

    @FXML
    private Label familyLabel;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox familyHBox;
    @FXML
    private StackPane alertStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cc = CollectionClass.getInstance();
        FontSettings fontSettings = new FontSettings();
        FavouriteCC favouriteCC = new FavouriteCC();


        if (cc.fontMap.get("specification").equals("System")) {
            fontSettings.read().getSystem().forEach((s1, s2) -> cc.fontMap.put(s1, s2));
            cc.fontMap.put("fonts", "Regular");
        } else if (cc.fontMap.get("specification").equals("Favourite")) {
            FontClass fontClass = fontSettings.read().getFavourite().get(cc.fontMap.get("family"));
            cc.fontMap.put("size", fontClass.getSize());
            cc.fontMap.put("example", fontClass.getExample());
            cc.fontMap.put("background_color", fontClass.getBackground_color());
            cc.fontMap.put("text_fill", fontClass.getText_fill());
            cc.fontMap.put("fonts", fontClass.getFonts());
        }
        familyHBox.getChildren().add(0, favouriteCC);
        new SceneClass().loadPane(menuPane, "/fxml/fonts/styles.fxml", 0.0, 0.0, 0.0, 0.0);
        cc.fontPaneArray[0] = "styles";
        familyLabel.setText(cc.fontMap.get("family"));


        Platform.runLater(() -> {
            NiceFontsController.getInstance().getSelectedFontLabel().setText(cc.fontMap.get("family").toUpperCase() + "    -");
            familyLabel.setStyle("-fx-font-family: '" + cc.fontMap.get("family") + "';-fx-font-size: 18px;-fx-font-style: normal;-fx-font-weight: normal;");

            if (cc.fontFavouriteList.contains(cc.fontMap.get("family"))) {
                favouriteCC.setFavourite(true);
                setClicked(true);
            }
        });
        favouriteCC.setOnMouseClicked(event -> {
            if (isClicked()) {
                favouriteCC.setFavourite(false);
                fontSettings.removeFavourite(cc.fontMap.get("family"));
                setClicked(false);
            } else {
                if (fontSettings.sizeFavourite() < 5) {
                    favouriteCC.setFavourite(true);
                    FontClass fontClass = new FontClass();
                    fontClass.setSize(cc.fontMap.get("size"));
                    fontClass.setExample(cc.fontMap.get("example"));
                    fontClass.setBackground_color(cc.fontMap.get("background_color"));
                    fontClass.setText_fill(cc.fontMap.get("text_fill"));
                    fontClass.setFonts(cc.fontMap.get("fonts"));

                    fontSettings.writeFavourite(cc.fontMap.get("family"), fontClass);
                    setClicked(true);
                } else {
                    cc.fontMap.put("alert", "Maximum favourite reached!");
                    AnchorPane pane = new SceneClass().loadPane(alertStackPane, "/fxml/other/alert.fxml");
                    pane.setStyle("-fx-background-color: #f1536e;");
                    pane.setTranslateY(pane.getPrefHeight());
                    new TransitionClass().translateAlert(pane.translateYProperty(), 0.0, 3, () -> alertStackPane.getChildren().remove(pane));
                }
            }
        });
    }

    @FXML
    private void handleBackOnAction() {
        NiceFontsController.getInstance().getMainPane().getChildren().remove(rootPane);
        NiceFontsController.getInstance().getSelectedFontLabel().setText("");
        if (cc.fontMap.get("specification").equals("Favourite")) {
            cc.fontFavouriteList.clear();
            NiceFontsController.getInstance().getFlowPane().getChildren().clear();
            new FontSettings().read().getFavourite().forEach((s, fontClass) -> cc.fontFavouriteList.add(s));
            cc.fontFavouriteList.forEach(s -> NiceFontsController.getInstance().getFlowPane().getChildren().add(NiceFontsController.getInstance().eachFonts(s)));
        }
        cc.fontPaneArray[0] = null;
        cc.uiPaneArray[0] = "home";
    }

    @FXML
    private void handleCharactersOnAction() {
        if (!cc.fontPaneArray[0].equals("character")) {
            new SceneClass().loadPane(menuPane, "/fxml/fonts/characters.fxml", 0.0, 0.0, 0.0, 0.0);
            cc.fontPaneArray[0] = "character";
        }
    }

    @FXML
    private void handleTestDriveOnAction() {
        if (!cc.fontPaneArray[0].equals("styles")) {
            new SceneClass().loadPane(menuPane, "/fxml/fonts/styles.fxml", 0.0, 0.0, 0.0, 0.0);
            cc.fontPaneArray[0] = "styles";
        }
    }

    @FXML
    private void handleBackgroundOnAction() {
        if (!cc.fontPaneArray[0].equals("background")) {
            new SceneClass().loadPane(menuPane, "/fxml/fonts/background.fxml", 0.0, 0.0, 0.0, 0.0);
            cc.fontPaneArray[0] = "background";
        }
    }

    private boolean isClicked() {
        return clicked;
    }

    private void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

}