package com.nicefonts.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;

public class FavouriteCC extends Label {

    public FavouriteCC() {
        super("");

        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M16.5,3C13.605,3,12,5.09,12,5.09S10.395,3,7.5,3C4.462,3,2,5.462,2,8.5c0,4.171,4.912,8.213,6.281,9.49 C9.858,19.46," +
                "12,21.35,12,21.35s2.142-1.89,3.719-3.36C17.088,16.713,22,12.671,22,8.5C22,5.462,19.538,3,16.5,3z");
        setGraphic(svgPath);
        svgPath.setScaleX(0.9);
        svgPath.setScaleY(0.9);
        getStyleClass().add("font-favourite");
    }

    public void setFavourite(boolean on) {
        this.favourite.set(on);
    }

    public boolean isFavourite() {
        return favourite.get();
    }

    public BooleanProperty favouriteProperty() {
        return favourite;
    }

    public BooleanProperty favourite = new BooleanPropertyBase(false) {
                @Override protected void invalidated() {
                    pseudoClassStateChanged(ON_PSEUDO_CLASS, get());
                }

                @Override public Object getBean() {
                    return FavouriteCC.this;
                }

                @Override public String getName() {
                    return "favourite";
                }
    };

    private static final PseudoClass ON_PSEUDO_CLASS = PseudoClass.getPseudoClass("favourite");



}




