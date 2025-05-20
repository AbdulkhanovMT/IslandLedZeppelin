package com.javarush.island.abdulkhanov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomiser {
    private Randomiser() {
    }

    public static int getRandomCount(int min, int max){
        return ThreadLocalRandom.current().nextInt(min,max);
    }
    public static boolean getRandomGender(){
        return getRandomCount(0,2)==0?true:false;
    }

    public static double getRandomWeight(double minWeight, double maxWeight) {
        return ThreadLocalRandom.current().nextDouble(minWeight, maxWeight);
    }
}
