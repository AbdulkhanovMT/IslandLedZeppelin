package com.javarush.island.abdulkhanov.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public abstract class Entity {
    private double weight;
    private ObjectMapper mapper = new YAMLMapper();

    public ObjectMapper getMapper() {
        return mapper;
    }

    public Entity() {
    }

    public Entity(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
