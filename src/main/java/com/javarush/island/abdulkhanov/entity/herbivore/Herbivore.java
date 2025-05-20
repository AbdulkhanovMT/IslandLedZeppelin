package com.javarush.island.abdulkhanov.entity.herbivore;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.animal.Animal;
import com.javarush.island.abdulkhanov.entity.limit.Limit;

public abstract class Herbivore extends Animal {

    public Herbivore() {
    }

    public Herbivore(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }
}
