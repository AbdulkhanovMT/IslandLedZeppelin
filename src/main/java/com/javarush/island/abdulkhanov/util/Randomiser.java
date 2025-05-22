package com.javarush.island.abdulkhanov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomiser {
    private Randomiser() {
    }

    public static int getRandomCount(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static boolean getRandomGender() {
        return getRandomCount(0, 2) == 0;
    }

    public static double getRandomWeight(double minWeight, double maxWeight) {
        minWeight = Math.round(minWeight);
        double randomWeight = 1.0 * ThreadLocalRandom.current().nextInt((int) minWeight, (int) maxWeight);
        return (randomWeight < 1) ? 1.0 : randomWeight;
    }
}
