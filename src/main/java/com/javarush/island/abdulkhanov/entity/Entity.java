package com.javarush.island.abdulkhanov.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.abdulkhanov.entity.ability.Eating;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;

import java.io.File;
import java.io.IOException;

public abstract class Entity implements Eating {
    private double weight;
    private final ObjectMapper mapper = new YAMLMapper();
    private final String icon = "";
    private boolean gender;
    private Limit entityLimit;

    public boolean isGender() {
        return gender;
    }

    public Limit getEntityLimit() {
        return entityLimit;
    }


    public Entity() {
    }

    public Entity(double weight, boolean gender, Limit entityLimit) {
        this.weight = weight;
        this.gender = gender;
        this.entityLimit = entityLimit;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }


    public Limit readConfig(){
        String statsPath = this.getStatsPath();
        File file = new File(statsPath);
        try {
            this.entityLimit = getMapper().readValue(file, Limit.class);
            return this.entityLimit;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eat(Cell cell){
        return false;
    }

    public abstract String getStatsPath();

    public Entity(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getIcon(){
        return icon;
    }
}
