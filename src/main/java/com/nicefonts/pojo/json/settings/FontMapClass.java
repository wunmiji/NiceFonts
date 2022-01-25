package com.nicefonts.pojo.json.settings;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;

public class FontMapClass {

    private HashMap<String, String> quote;
    private HashMap<String, String> system;
    private HashMap<String, FontClass> favourite;

    public FontMapClass () {
        quote = new HashMap<>();
        QuoteClass quoteClass = new QuoteClass();
        Object[] objects = quoteClass.quoteList.keySet().toArray();
        Object key = objects[new Random().nextInt(objects.length)];
        quote.put("name", String.valueOf(key));
        quote.put("quote", quoteClass.quoteList.get(key.toString()));
        quote.put("Date", LocalDate.now().toString());

        system = new HashMap<>();
        getSystem().put("size", "30");
        getSystem().put("example", "The Quick Brown Fox Jumps Over The Lazy Dog.");
        getSystem().put("background_color", "#FFFFFF");
        getSystem().put("text_fill", "#243665");

        favourite = new HashMap<>();
    }

    public HashMap<String, String> getQuote() {
        return quote;
    }

    public HashMap<String, String> getSystem() {
        return system;
    }

    public HashMap<String, FontClass> getFavourite() {
        return favourite;
    }

}
