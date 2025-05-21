package com.javarush.island.abdulkhanov.entity.limit;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.abdulkhanov.entity.animal.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@JsonPropertyOrder({"maxSpeed", "maxWeight", "maxCountInCell", "maxFood"})
public class Limit<T extends Animal> {

    private String maxSpeed;
    private String maxWeight;
    private String maxCountInCell;
    private String maxFood;

    public Limit() {
    }

    public Limit(String maxSpeed, String maxWeight, String maxCountInCell, String maxFood) {
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
        this.maxCountInCell = maxCountInCell;
        this.maxFood = maxFood;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public String getMaxCountInCell() {
        return maxCountInCell;
    }

    public String getMaxFood() {
        return maxFood;
    }

//    public void readConfig(T animal){
//        String statsPath = animal.getStatsPath();
//        try {
//            String yamlConfig = Files.readString(Path.of(statsPath));
//            this = mapper.readValue(yamlConfig, Limit.class);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
