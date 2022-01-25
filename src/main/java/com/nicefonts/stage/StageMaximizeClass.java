package com.nicefonts.stage;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public final class StageMaximizeClass {

    private double x, y;
    private boolean stageMaximize;

    public StageMaximizeClass() {
        setStageMaximize(true);
    }

    private double getX() {
        return x;
    }

    private void setX(double x) {
        this.x = x;
    }

    private double getY() {
        return y;
    }

    private void setY(double y) {
        this.y = y;
    }

    private boolean isStageMaximize() {
        return stageMaximize;
    }

    private void setStageMaximize(boolean stageMaximize) {
        this.stageMaximize = stageMaximize;
    }

    private void minimize(Stage stage, Rectangle2D bounds) {
        setX(stage.getX());
        setY(stage.getY());
        stage.setWidth(bounds.getWidth() + 8);
        stage.setHeight(bounds.getHeight() + 8);
//            stage.setX((bounds.getWidth() - stage.getWidth()) / 2);
//            stage.setY((bounds.getHeight() - stage.getHeight()) / 3);
        stage.setX(-4);
        stage.setY(-4);
    }

    private void maximize(Stage stage, Rectangle2D bounds) {
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(750);
        stage.setHeight(500);
        stage.setX(getX());
        stage.setY(getY());
    }

    public void handleMaxMin (AnchorPane rootPane) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        if (isStageMaximize()) {
            minimize(stage, bounds);
            setStageMaximize(false);
        } else {
            maximize(stage, bounds);
            setStageMaximize(true);
        }
    }

    public void hideStage (AnchorPane rootPane) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleMouseClicked (AnchorPane rootPane) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        if (!isStageMaximize()) {
            maximize(stage, bounds);
            setStageMaximize(true);
        } else {
            minimize(stage, bounds);
            setStageMaximize(false);
        }
    }

    public void handleMouseDragged (AnchorPane rootPane) {
        if (!isStageMaximize()) {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            maximize(stage, bounds);
            setStageMaximize(true);
        }
    }
}
