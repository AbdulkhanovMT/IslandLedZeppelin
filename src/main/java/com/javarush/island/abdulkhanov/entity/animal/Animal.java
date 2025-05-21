package com.javarush.island.abdulkhanov.entity.animal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.ability.Eating;
import com.javarush.island.abdulkhanov.entity.ability.Moveable;
import com.javarush.island.abdulkhanov.entity.ability.Reproducible;
import com.javarush.island.abdulkhanov.entity.limit.Limit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Animal extends Entity implements Eating, Moveable, Reproducible {
    private int age;
    private boolean gender;
    private Limit animalLimit;
    private final String icon = "ɸ";

    public abstract String getStatsPath();

    private final String statsPath = null;

    public Animal() {
    }

    public Animal(double weight, boolean gender, Limit animalLimit) {
        super(weight);
        this.gender = gender;
        this.animalLimit = animalLimit;
    }

    public ObjectMapper getMapper(){
        return super.getMapper();
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
    };

    public boolean getGender() {
        return gender;
    }

    public Limit getAnimalLimit() {
        return animalLimit;
    }

    @Override
    public String toString() {
        return "Animal: " + this.getClass().getName();
    }

    public String getIcon(){
        return icon;
    };
}
