package com.javarush.island.abdulkhanov.entity.animal;

public enum TypeOfEntity {
    WOLF ("Wolf"),
    BEAR ("Bear"),
    FOX ("Fox"),
    CONSTRICTOR ("Constrictor"),
    EAGLE ("Eagle"),
    HORSE ("Horse"),
    DEER ("Deer"),
    BOAR ("Boar"),
    BUFFALO ("Buffalo"),
    GOAT ("Goat"),
    SHEEP ("Sheep"),
    DUCK ("Duck"),
    MOUSE ("Mouse"),
    RABBIT ("Rabbit"),
    CATERPILLAR ("Caterpillar"),
    PLANT("Plant");

    private String nameOfType;

    TypeOfEntity(String nameOfType){
        this.nameOfType = nameOfType;
    }
}
