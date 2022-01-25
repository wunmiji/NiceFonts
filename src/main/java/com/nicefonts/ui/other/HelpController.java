package com.nicefonts.ui.other;

import com.nicefonts.other.CollectionClass;
import com.nicefonts.ui.main.NiceFontsController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class HelpController {

    @FXML
    private AnchorPane rootPane;


    @FXML
    private void handleCancelButtonOnAction() {
        NiceFontsController.getInstance().getMainPane().getChildren().remove(rootPane);
        CollectionClass.getInstance().uiPaneArray[0] = "home";
    }
}
