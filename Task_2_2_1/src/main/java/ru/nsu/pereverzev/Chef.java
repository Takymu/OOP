package ru.nsu.pereverzev;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a Chef that prepares dishes in a kitchen.
 */
public class Chef extends Thread {
    private final double speed;
    private final AtomicBoolean endOfDay;
    private final QueueSafe<Dish> storage;
    private final QueueSafe<Dish> queue;

    /**
     * Constructs a Chef with specified speed and storage.
     */
    Chef(double speed, AtomicBoolean endOfDay, QueueSafe<Dish> storage, QueueSafe<Dish> queue) {
        this.speed = speed;
        this.endOfDay = endOfDay;
        this.storage = storage;
        this.queue = queue;
    }

    /**
     * Runs the chef's cooking process, taking dishes from the queue and cooking them.
     */
    public void run() {
        try {
            while (true) {
                synchronized (this.queue) {
                    while (!endOfDay.get() && queue.isEmpty()) {
                        queue.wait();
                    }
                }
                if (endOfDay.get()) {
                    break;
                }
                Dish dish = queue.take();
                if (dish == null) {
                    continue;
                }
                Thread.sleep((long) (1000 / speed));
                dish.setStatus("cooked");
                System.out.println(dish.getStatus());
                storage.add(dish);
                synchronized (this.storage) {
                    storage.notify();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
