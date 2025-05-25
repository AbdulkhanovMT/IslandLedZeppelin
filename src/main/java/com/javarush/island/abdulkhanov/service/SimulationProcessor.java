package com.javarush.island.abdulkhanov.service;

import com.javarush.island.abdulkhanov.Simulation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationProcessor extends Thread {
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final Simulation simulation;
    private final List<Runnable> services;
    private final int PERIOD = 1_000;
    private ScheduledExecutorService mainPool;

    public SimulationProcessor(Simulation simulation, List<Runnable> services) {
        this.simulation = simulation;
        this.services = services;
    }

    @Override
    public void run() {
        mainPool = Executors.newSingleThreadScheduledExecutor();
        mainPool.scheduleAtFixedRate(this::doOneStepGame, 0, PERIOD, TimeUnit.MILLISECONDS);
    }

    private void doOneStepGame() {
        if (!simulation.isFinished()) {
            try (ExecutorService servicePool = Executors.newFixedThreadPool(CORE_POOL_SIZE)) {
                services.forEach(servicePool::submit);
                servicePool.shutdown();
            }
        } else {
            mainPool.shutdown();
        }
    }
}
