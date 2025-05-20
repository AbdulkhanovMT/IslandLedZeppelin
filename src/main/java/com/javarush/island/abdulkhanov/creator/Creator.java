package com.javarush.island.abdulkhanov.creator;


import java.lang.reflect.InvocationTargetException;

public abstract class Creator<T> {
    public abstract T create(Class<? extends T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
