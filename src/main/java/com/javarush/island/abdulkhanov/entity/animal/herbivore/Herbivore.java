package com.javarush.island.abdulkhanov.entity.animal.herbivore;

import com.javarush.island.abdulkhanov.entity.animal.Animal;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;

public abstract class Herbivore extends Animal {

    public Herbivore() {
    }

    public Herbivore(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }

    public boolean eat(Cell cell){
        boolean foodFound = false;
        if (safeFindFood(cell)) {
            return true;
        }
        if(safeTryToEat(cell)){

        }
        return false;
    }

    private boolean safeTryToEat(Cell cell) {
        return false;
    }

    public boolean safeFindFood(Cell cell){
        return false;
    }
}
