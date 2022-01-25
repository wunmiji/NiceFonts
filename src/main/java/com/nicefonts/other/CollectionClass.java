package com.nicefonts.other;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class CollectionClass {
    private static final CollectionClass CC = new CollectionClass();

    public ObservableList<String> specificationList = FXCollections.observableArrayList("Specification", "System", "Favourite");
    public ObservableList<String> styleList = FXCollections.observableArrayList();

    public String[] fontPaneArray = new String[1];
    public String[] uiPaneArray = new String[1];

    public ObservableList<String> fontSystemList = FXCollections.observableArrayList();
    public ObservableList<String> fontFavouriteList = FXCollections.observableArrayList();

    public ObservableMap<String,String> fontMap = FXCollections.observableHashMap();

    public String[] alphabetArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public String[] numberArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public String[] symbolArray = {"`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "{", "}", "[", "]", "\\", "|", ";", ":", "'", "<", ">", ",", ".", "/"};


    private CollectionClass() {
    }

    public static CollectionClass getInstance() {
        if (CC == null) {
            return CC;
        } else {
            return CC;
        }
    }
}
