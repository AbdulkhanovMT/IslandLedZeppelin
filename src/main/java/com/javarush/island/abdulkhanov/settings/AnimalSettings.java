package com.javarush.island.abdulkhanov.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.abdulkhanov.entity.Entity;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnimalSettings {
    private static Map<String, Map<String, Integer>> foodMap = new LinkedHashMap<>();
    private static final ObjectMapper animalPercentage = new YAMLMapper();
    private static final String percentagePath = "src/main/java/com/javarush/island/abdulkhanov/config/animalconfig.yaml";

    private AnimalSettings() {
    }

    static{
       loadFoodMap();
    }

    public static void loadFoodMap() {
        File yamlAnimalPercentageFile = new File(percentagePath);
        try {
            foodMap = animalPercentage.readValue(yamlAnimalPercentageFile, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> getTargetMap(Class<? extends Entity> clazz){
        return foodMap.get(clazz.getName());
    }

}
