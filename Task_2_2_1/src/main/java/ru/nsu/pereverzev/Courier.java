package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Courier extends Thread{
    private int volume;
    private AtomicBoolean endOfDay;
    private QueueSafe<Dish> storage;
    private ArrayList<Dish> trunk;

    Courier(int volume, AtomicBoolean endOfDay,
        QueueSafe<Dish> storage) {
        this.volume = volume;
        this.endOfDay = endOfDay;
        this.storage = storage;
        this.trunk = new ArrayList<Dish>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this.storage) {
                    while (!endOfDay.get() && storage.isEmpty()) {
                        storage.wait();
                    }
                }
                if(endOfDay.get()) {
                    break;
                }
                for (int i = 0; i < volume; i++) {
                    Dish dish = storage.take();
                    if(dish == null) {
                        continue;
                    }
                    this.trunk.add(dish);
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
