package com.nicefonts.settings;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nicefonts.pojo.json.settings.FontMapClass;

import java.io.File;
import java.io.IOException;

public class FontSettings {

    private final String settings = "settings/settings.json";
    private final String google = "settings/google.json";

    public FontSettings () {
        File file = new File ("settings");
        if (!file.exists()) file.mkdir();
    }

    private void initConfig (ObjectMapper objectMapper) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            objectMapper.writeValue(new File(settings), new FontMapClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSystem (String key, String value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            JsonNode rootNode = objectMapper.readTree(new File(settings));

            JsonNode node = rootNode.get("system");
            ((ObjectNode) node).put(key, value);

            objectMapper.writeValue(new File(settings), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeQuote (String quote, String name, String date) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            JsonNode rootNode = objectMapper.readTree(new File(settings));

            JsonNode node = rootNode.get("quote");
            ((ObjectNode) node).put("quote", quote);
            ((ObjectNode) node).put("name", name);
            ((ObjectNode) node).put("Date", date);

            objectMapper.writeValue(new File(settings), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFavourite (String key, Object pojo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            JsonNode rootNode = objectMapper.readTree(new File(settings));

            JsonNode node = rootNode.get("favourite");
            ((ObjectNode) node).putPOJO(key, pojo);

            objectMapper.writeValue(new File(settings), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFavourite (String family, String key, String value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            JsonNode rootNode = objectMapper.readTree(new File(settings));

            JsonNode node = rootNode.get("favourite");
            node = node.get(family);
            ((ObjectNode) node).put(key, value);

            objectMapper.writeValue(new File(settings), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFavourite (String key) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            JsonNode rootNode = objectMapper.readTree(new File(settings));

            JsonNode node = rootNode.get("favourite");
            ((ObjectNode) node).remove(key);

            objectMapper.writeValue(new File(settings), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int sizeFavourite () {
        int size = 0;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            JsonNode rootNode = objectMapper.readTree(new File(settings));

            JsonNode node = rootNode.get("favourite");
            size = node.size();

            objectMapper.writeValue(new File(settings), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public FontMapClass read () {
        ObjectMapper objectMapper = new ObjectMapper();
        FontMapClass fc = null;
        if (!new File(settings).exists()) initConfig(objectMapper);
        try {
            fc = objectMapper.readValue(new File(settings), FontMapClass.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fc;
    }


}
