package com.javarush.island.abdulkhanov.creator;


public abstract class Creator<T> {
    public abstract T create(Class<? extends T> tClass);
}
