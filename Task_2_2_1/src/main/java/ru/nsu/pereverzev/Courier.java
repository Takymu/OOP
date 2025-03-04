package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Courier extends Thread{
    private int volume;
    private AtomicBoolean endOfDay;
    private ArrayBlockingQueue<Dish> storage;
    private ArrayList<Dish> trunk;
    Courier(int volume, AtomicBoolean endOfDay,
            ArrayBlockingQueue<Dish> storage) {
        this.volume = volume;
        this.endOfDay = endOfDay;
        this.storage = storage;
        this.trunk = new ArrayList<Dish>();
    }

    @Override
    public void run() {
        try {
            while (!endOfDay.get()) {
                for (int i = 0; i < volume; i++) {
                    this.trunk.add(storage.take());
                }
                Thread.sleep(1000);
                
                for (Dish dish : this.trunk) {
                    dish.setStatus("delivered");
                    System.out.println(dish.getStatus());
                }

                this.trunk.clear();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
