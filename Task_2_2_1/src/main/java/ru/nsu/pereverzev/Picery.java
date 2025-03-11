package ru.nsu.pereverzev;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a Picery that manages chefs and couriers for dish preparation and delivery.
 */
public class Picery {

    ArrayList<Chef> chefs;
    ArrayList<Courier> couriers;
    QueueSafe<Dish> storage;
    QueueSafe<Dish> queue;
    AtomicBoolean endOfDay = new AtomicBoolean(false);

    /**
     * Constructs a Picery with the specified number of chefs and couriers, and storage size.
     */
    Picery(int numChefs, int numCouriers, int storageSize, Config config) {
        this.chefs = new ArrayList<Chef>(numChefs);
        this.couriers = new ArrayList<Courier>(numCouriers);
        this.storage = new QueueSafe<Dish>(storageSize);
        this.queue = new QueueSafe<Dish>(10);
        Random random = new Random();
        for (int i = 0; i < numChefs; i++) {
            this.chefs.add(new Chef(random.nextDouble() + 0.1, endOfDay, storage, queue, config.getMean_cook_time_ms()));
        }
        for (int i = 0; i < numCouriers; i++) {
            this.couriers.add(new Courier(random.nextInt(10) + 1, endOfDay, storage, config.getDelivery_time_ms()));
        }
    }

    /**
     * Starts the chefs and couriers for their respective tasks.
     */
    public void run() {
        for (Chef chef : chefs) {
            chef.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    /**
     * Adds a dish to the storage.
     */
    synchronized public void addDish(Dish dish) {
        this.storage.add(dish);
        synchronized (this.storage) {
            this.storage.notify();
        }
    }

    /**
     * Signals the end of the day for chefs and couriers.
     */
    public void endOfDay() {
        this.endOfDay.set(true);
        synchronized (this.queue) {
            this.queue.notifyAll();
        }
        synchronized (this.storage) {
            this.storage.notifyAll();
        }
    }

    /**
     * Queues a dish for delivery.
     */
    public void queueDish(Dish dish) {
        dish.setStatus("queued");
        System.out.println(dish.getId() + " " + dish.getStatus());
        this.queue.add(dish);
        synchronized (this.queue) {
            this.queue.notify();
        }
    }
}