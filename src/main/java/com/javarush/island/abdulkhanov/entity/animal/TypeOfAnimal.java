package com.javarush.island.abdulkhanov.entity.animal;

public enum TypeOfAnimal {
    WOLF ("Wolf"),
    RABBIT ("Rabbit");

    private String nameOfType;

    TypeOfAnimal(String nameOfType){
        this.nameOfType = nameOfType;
    }
}
