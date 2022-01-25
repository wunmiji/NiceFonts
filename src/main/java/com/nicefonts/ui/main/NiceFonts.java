package com.nicefonts.ui.main;

import com.nicefonts.stage.SceneClass;
import javafx.application.Application;
import javafx.stage.Stage;

public class NiceFonts extends Application {
    @Override
    public void start(Stage stage) {
        new SceneClass().appStage(stage, "/fxml/main/nice_fonts.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
