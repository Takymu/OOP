package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a Courier that delivers dishes.
 */
public class Courier extends Thread {
    private final int volume;
    private final AtomicBoolean endOfDay;
    private final QueueSafe<Dish> storage;
    private final ArrayList<Dish> trunk;
    private final long deliveryTime;

    /**
     * Constructs a Courier with specified volume and storage.
     */
    Courier(int volume, AtomicBoolean endOfDay, QueueSafe<Dish> storage,
            long deliveryTime) {
        this.volume = volume;
        this.endOfDay = endOfDay;
        this.storage = storage;
        this.trunk = new ArrayList<Dish>();
        this.deliveryTime = deliveryTime;
    }

    /**
     * Runs the courier's delivery process, taking dishes from storage and delivering them.
     */
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this.storage) {
                    while (!endOfDay.get() && storage.isEmpty()) {
                        storage.wait();
                    }
                }
                if (endOfDay.get()) {
                    break;
                }
                for (int i = 0; i < volume; i++) {
                    Dish dish = storage.take();
                    if (dish == null) {
                        continue;
                    }
                    this.trunk.add(dish);
                }
                Thread.sleep(this.deliveryTime);
                for (Dish dish : this.trunk) {
                    dish.setStatus("delivered");
                    System.out.println(dish.getId() + " " + dish.getStatus());
                }
                this.trunk.clear();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
