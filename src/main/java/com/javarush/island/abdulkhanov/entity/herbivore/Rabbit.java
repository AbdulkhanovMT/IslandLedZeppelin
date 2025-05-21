package com.javarush.island.abdulkhanov.entity.herbivore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.entity.plant.Plant;
import com.javarush.island.abdulkhanov.gamefield.Cell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Rabbit extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/rabbit_limit.yaml";
    private final String icon = "\uD83D\uDC07";

    public Rabbit() {
    }

    public Rabbit(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }



    @Override
    public boolean eat(Entity plant) {
        return false;
    }

    public ObjectMapper getMapper(){
        return super.getMapper();
    }

    @Override
    public boolean move(Cell cell) {
        return false;
    }

    @Override
    public boolean reproduce(Entity entity) {
        return false;
    }

    public String getStatsPath() {
        return statsPath;
    }

    public Limit readConfig(){
        String statsPath = this.getStatsPath();
        File file = new File(statsPath);
        try {
            String yamlConfig = Files.readString(Path.of(statsPath));
            Limit limit = getMapper().readValue(file, Limit.class);
            return limit;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
