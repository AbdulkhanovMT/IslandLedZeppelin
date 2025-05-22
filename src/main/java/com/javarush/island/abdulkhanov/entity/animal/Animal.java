package com.javarush.island.abdulkhanov.entity.animal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.ability.Moveable;
import com.javarush.island.abdulkhanov.entity.ability.Reproducible;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;

public abstract class Animal extends Entity implements Moveable, Reproducible {
    private int age;
    private boolean gender;
    private Limit animalLimit;
    private boolean isStarving;

    public Animal() {
    }

    public Animal(double weight, boolean gender, Limit animalLimit) {
        super(weight);
        this.gender = gender;
        this.animalLimit = animalLimit;
    }

    public abstract String getStatsPath();

    private final String statsPath = null;

    public boolean isStarving() {
        return isStarving;
    }

    @Override
    public boolean move(Cell cell){
        return false;
    }

    @Override
    public boolean reproduce(Cell cell){
        return false;
    }

    public ObjectMapper getMapper(){
        return super.getMapper();
    }

    public boolean getGender() {
        return gender;
    }

    public Limit getEntityLimit() {
        return animalLimit;
    }

    @Override
    public String toString() {
        return "Animal: " + this.getClass().getName();
    }

}
