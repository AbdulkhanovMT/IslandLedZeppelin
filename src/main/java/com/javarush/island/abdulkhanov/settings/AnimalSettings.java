package com.javarush.island.abdulkhanov.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnimalSettings {
    private Map<String, Map<String, Integer>> foodMap = new LinkedHashMap<>();
    private final ObjectMapper animalPercentage = new YAMLMapper();
    private final String percentagePath = "src/main/java/com/javarush/island/abdulkhanov/config/animalconfig.yaml";

    public AnimalSettings() {
    }

    public Map<String, Map<String, Integer>> getFoodMap() {
        return foodMap;
    }

    public void loadFoodMap() {
        File yamlAnimalPercentageFile = new File(percentagePath);
        try {
            foodMap = animalPercentage.readValue(yamlAnimalPercentageFile, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectMapper getAnimalPercentage() {
        return animalPercentage;
    }

    public String getPercentagePath() {
        return percentagePath;
    }
}
