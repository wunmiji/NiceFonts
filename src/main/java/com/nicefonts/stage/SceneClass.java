package com.nicefonts.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneClass {

    private double xOffSet;
    private double yOffSet;


    private void movingStage(Stage stage, Parent root) {
        root.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        root.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    private void movingImageTitleStage (Stage stage, Parent root) {
        movingStage(stage, root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/window-logo.png")));
        stage.setTitle("NiceNote");
    }

    private AnchorPane loadFXML(String s) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(s));
        } catch (IOException | NullPointerException ex) {
        }
        return root;
    }

    public void appStage (Stage stage, String s) {
        Parent root = loadFXML(s);
        movingImageTitleStage(stage, root);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void setConstraint(Pane pane, Double top, Double right, Double bottom, Double left) {
        AnchorPane.setBottomAnchor(pane, bottom);
        AnchorPane.setTopAnchor(pane, top);
        AnchorPane.setRightAnchor(pane, right);
        AnchorPane.setLeftAnchor(pane, left);
    }

    public void loadPane(Pane parent, String location, Double top, Double right, Double buttom, Double left) {
        parent.getChildren().clear();
        AnchorPane pane = loadFXML(location);
        parent.getChildren().add(pane);
        setConstraint(pane, top, right, buttom, left);
    }

    public AnchorPane loadPane(StackPane parent, String location) {
        AnchorPane pane = loadFXML(location);
        parent.getChildren().add(pane);
        return pane;
    }

}