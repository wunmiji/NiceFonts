package com.nicefonts.ui.fonts;

import com.nicefonts.other.CollectionClass;
import com.nicefonts.settings.FontSettings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class StylesController implements Initializable {

    private CollectionClass cc;


    @FXML
    private TextField exampleText;
    @FXML
    private ComboBox<String> styleComboBox;
    @FXML
    private Slider sizeSlider;
    @FXML
    private Label exampleLabel;
    @FXML
    private ColorPicker textColorPicker;
    @FXML
    private ColorPicker backgroundColorPicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cc = CollectionClass.getInstance();
        FontSettings fontSettings = new FontSettings();

        cc.styleList.clear();
        styleComboBox.setItems(cc.styleList);
        switchStyle(cc.fontMap.get("fonts"));
        styleLabel();

        loadStyle(cc.fontMap.get("family"));
        exampleText.setText(cc.fontMap.get("example"));
        exampleLabel.setText(cc.fontMap.get("example"));
        sizeSlider.setValue(Double.parseDouble(cc.fontMap.get("size")));
        textColorPicker.setValue(Color.web(cc.fontMap.get("text_fill")));
        backgroundColorPicker.setValue(Color.web(cc.fontMap.get("background_color")));

        exampleText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                exampleLabel.setText(newValue);
                cc.fontMap.put("example", newValue);
                if (cc.fontMap.get("specification").equals("System")) {
                    fontSettings.writeSystem("example", newValue);
                } else if (cc.fontMap.get("specification").equals("Favourite")) {
                    fontSettings.writeFavourite(cc.fontMap.get("family"), "example", newValue);
                }

            }
        });
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String size = String.valueOf(newValue.intValue());
                cc.fontMap.put("size", size);
                styleLabel();
                if (cc.fontMap.get("specification").equals("System")) {
                    fontSettings.writeSystem("size", size);
                } else if (cc.fontMap.get("specification").equals("Favourite")) {
                    fontSettings.writeFavourite(cc.fontMap.get("family"), "size", size);
                }
            }
        });
        styleComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switchStyle(newValue);
                styleLabel();
                cc.fontMap.put("fonts", newValue);
                if (cc.fontMap.get("specification").equals("Favourite")) {
                    fontSettings.writeFavourite(cc.fontMap.get("family"), "fonts", newValue);
                }
            }

        });
        textColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String c = color(newValue.toString());
                cc.fontMap.put("text_fill", c);
                styleLabel();
                if (cc.fontMap.get("specification").equals("System")) {
                    fontSettings.writeSystem("text_fill", c);
                } else if (cc.fontMap.get("specification").equals("Favourite")) {
                    fontSettings.writeFavourite(cc.fontMap.get("family"), "text_fill", c);
                }
            }
        });
        backgroundColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String c = color(newValue.toString());
                cc.fontMap.put("background_color", c);
                styleLabel();
                if (cc.fontMap.get("specification").equals("System")) {
                    fontSettings.writeSystem("background_color", c);
                } else if (cc.fontMap.get("specification").equals("Favourite")) {
                    fontSettings.writeFavourite(cc.fontMap.get("family"), "background_color", c);
                }
            }
        });

    }

    private void loadStyle(String family) {
        Font.getFontNames(family).forEach((t) -> {
            String rr = t.replace(family, "");
            if (rr.isEmpty()) {
                rr = rr.replace("", "Regular");
            }
            cc.styleList.addAll(rr.trim());
        });
        styleComboBox.getSelectionModel().select(cc.fontMap.get("fonts"));
    }

    private void styleLabel () {
        exampleLabel.setStyle("-fx-background-color: " + cc.fontMap.get("background_color") + ";-fx-text-fill: "
                + cc.fontMap.get("text_fill") + ";-fx-font-family: '" + cc.fontMap.get("family")
                + "';-fx-font-size: " + cc.fontMap.get("size") + "px;-fx-font-style:" + cc.fontMap.get("style")
                + ";-fx-font-weight:" + cc.fontMap.get("weight") + ";");
    }

    private String color (String c) {
        return c.replace("0x", "#");
    }

    private void switchStyle(String newValue) {
        switch (newValue) {
            case "Regular":
                cc.fontMap.put("style", "normal");
                cc.fontMap.put("weight", "normal");
                break;
            case "Italic":
                cc.fontMap.put("style", "italic");
                cc.fontMap.put("weight", "normal");
                break;
            case "Bold Italic":
                cc.fontMap.put("style", "italic");
                cc.fontMap.put("weight", "bold");
                break;
            case "Bold":
                cc.fontMap.put("style", "normal");
                cc.fontMap.put("weight", "bold");
                break;
            case "Demibold":
                cc.fontMap.put("style", "normal");
                cc.fontMap.put("weight", "600");
                break;
            case "Demibold Italic":
                cc.fontMap.put("style", "italic");
                cc.fontMap.put("weight", "600");
                break;
            case "Oblique":
                cc.fontMap.put("style", "oblique");
                cc.fontMap.put("weight", "normal");
                break;
            case "Bold Oblique":
                cc.fontMap.put("style", "oblique");
                cc.fontMap.put("weight", "bold");
                break;
        }
    }


}
