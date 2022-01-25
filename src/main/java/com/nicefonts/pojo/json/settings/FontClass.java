package com.nicefonts.pojo.json.settings;

public class FontClass {

    private String background_color;
    private String text_fill;
    private String fonts;
    private String size;
    private String example;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getText_fill() {
        return text_fill;
    }

    public void setText_fill(String text_fill) {
        this.text_fill = text_fill;
    }

    public String getFonts() {
        return fonts;
    }

    public void setFonts(String fonts) {
        this.fonts = fonts;
    }
}