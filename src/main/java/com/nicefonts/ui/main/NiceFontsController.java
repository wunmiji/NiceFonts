package com.nicefonts.ui.main;

import com.nicefonts.other.CollectionClass;
import com.nicefonts.pojo.json.settings.QuoteClass;
import com.nicefonts.settings.FontSettings;
import com.nicefonts.stage.SceneClass;
import com.nicefonts.stage.StageMaximizeClass;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class NiceFontsController implements Initializable {

    private static NiceFontsController instance;

    private CollectionClass cc;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField searchText;
    @FXML
    private StackPane mainPane;
    @FXML
    private Label selectedFontLabel;
    @FXML
    private StackPane placeholderStackPane;

    public NiceFontsController() {
        instance = this;
    }

    public static NiceFontsController getInstance() {
        return instance;
    }

    public FlowPane getFlowPane () {
        return  flowPane;
    }

    public StackPane getMainPane () {
        return mainPane;
    }

    public Label getSelectedFontLabel () {
        return selectedFontLabel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cc = CollectionClass.getInstance();
        FontSettings fontSettings = new FontSettings();

        comboBox.setItems(cc.specificationList);
        cc.fontMap.put("specification", "");
        cc.uiPaneArray[0] = "home";
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (cc.fontMap.get("specification").equals("System")) {
                flowPane.getChildren().clear();
                if (newValue.isEmpty()) {
                    emptySearch(false);
                    cc.fontSystemList.forEach(s -> flowPane.getChildren().add(eachFonts(s)));
                } else {
                    if (cc.fontSystemList.stream().noneMatch(s -> s.toLowerCase().contains(newValue))) emptySearch(true);
                    else {
                        emptySearch(false);
                        cc.fontSystemList.forEach(s -> {
                            if (s.toLowerCase().contains(newValue)) flowPane.getChildren().add(eachFonts(s));
                        });
                    }
                }
            } else if (cc.fontMap.get("specification").equals("Favourite")) {
                flowPane.getChildren().clear();
                if (newValue.isEmpty()) {
                    emptySearch(false);
                    cc.fontFavouriteList.forEach(s -> flowPane.getChildren().add(eachFonts(s)));
                }  else {
                    if (cc.fontFavouriteList.stream().noneMatch(s -> s.toLowerCase().contains(newValue))) emptySearch(true);
                    else {
                        emptySearch(false);
                        cc.fontFavouriteList.forEach(s -> {
                            if (s.toLowerCase().contains(newValue)) flowPane.getChildren().add(eachFonts(s));
                        });
                    }
                }
            }
        });

        Platform.runLater(() -> {
            cc.fontSystemList.addAll(Font.getFamilies());
            String date = fontSettings.read().getQuote().get("Date");
            if (!LocalDate.parse(date).equals(LocalDate.now())) {
                QuoteClass quoteClass = new QuoteClass();
                Object[] objects = quoteClass.quoteList.keySet().toArray();
                Object key = objects[new Random().nextInt(objects.length)];
                fontSettings.writeQuote(quoteClass.quoteList.get(key.toString()), String.valueOf(key), LocalDate.now().toString());
            }
            fontSettings.read().getQuote().forEach((s, s2) -> cc.fontMap.put(s, s2));
            specification();
        });
    }

    @FXML
    private void handleCancelButtonOnAction() {
        Platform.exit();
    }

    @FXML
    private void handleMinimizeButtonOnAction() {
        new StageMaximizeClass().hideStage(rootPane);
    }

    @FXML
    private void handleLogoButtonOnAction() {
        if (cc.uiPaneArray[0].equals("home")) {
            new SceneClass().loadPane(mainPane, "/fxml/other/help.fxml");
            cc.uiPaneArray[0] = "help";
        }
    }

    @FXML
    private void handleComboBoxOnAction() {
        flowPane.getChildren().clear();
        searchText.clear();
        switch (comboBox.getSelectionModel().getSelectedItem()) {
            case "Specification":
                specification();
                break;
            case "System":
                placeholderStackPane.setVisible(false);
                cc.fontSystemList.forEach(s -> flowPane.getChildren().add(eachFonts(s)));
                break;
            case "Favourite":
                placeholderStackPane.setVisible(false);
                cc.fontFavouriteList.clear();
                new FontSettings().read().getFavourite().forEach((s, fontClass) -> cc.fontFavouriteList.add(s));
                cc.fontFavouriteList.forEach(s -> flowPane.getChildren().add(eachFonts(s)));
                break;
        }
        cc.fontMap.put("specification", comboBox.getSelectionModel().getSelectedItem());
    }

    public Label eachFonts (String name) {
        var label = new Label(name);
        label.setPrefSize(175, 100);
        label.setWrapText(true);
        label.setStyle("-fx-background-color: -fx-secondary;-fx-text-fill: -fx-primary;-fx-padding:  0 5px 0 5px;" + "-fx-font-family: '" + name + "';-fx-font-size: " + 30
                  + "px;-fx-font-style: normal;-fx-font-weight: normal;");
        label.setOnMouseClicked(event -> {
            cc.fontMap.put("family", name);
            new SceneClass().loadPane(mainPane, "/fxml/other/font.fxml");
            cc.uiPaneArray[0] = "font";
        });
        return label;
    }

    private void emptySearch (boolean b) {
        placeholderStackPane.setVisible(b);
        if (b) {
            var label = new Label("Nothing Found!");
            label.setStyle("-fx-text-fill: -fx-white;-fx-font-size: 20px;");
            var imageView = new ImageView(new Image(getClass().getResourceAsStream("/icon/nothing.png")));
            var box = new VBox(imageView, label);
            box.setAlignment(Pos.CENTER);
            box.setPadding(new Insets(0.0, 0.0, 100.0, 0.0));
            placeholderStackPane.getChildren().setAll(box);
        }
    }

    private void specification () {
        placeholderStackPane.getChildren().clear();
        new SceneClass().loadPane(placeholderStackPane, "/fxml/main/specification.fxml");
        placeholderStackPane.setVisible(true);
    }

}
