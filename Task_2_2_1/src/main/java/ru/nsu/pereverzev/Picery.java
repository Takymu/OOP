package ru.nsu.pereverzev;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;
import java.util.Random;

public class Picery {
    ArrayList<Chef> chefs;
    ArrayList<Courier> couriers;
    ArrayBlockingQueue<Dish> storage;
    LinkedBlockingQueue<Dish> queue;
    AtomicBoolean endOfDay = new AtomicBoolean(false);
    Picery(int numChefs, int numCouriers, int storageSize) {
        this.chefs = new ArrayList<Chef>(numChefs);
        this.couriers = new ArrayList<Courier>(numCouriers);
        this.storage = new ArrayBlockingQueue<Dish>(storageSize);
        this.queue = new LinkedBlockingQueue<Dish>();
        Random random = new Random();
        for (int i = 0; i < numChefs; i++) {
            this.chefs.add(new Chef(random.nextDouble() + 0.1,
                           endOfDay, storage, queue));
        }
        for (int i = 0; i < numCouriers; i++) {
            this.couriers.add(new Courier(random.nextInt(10) + 1,
                              endOfDay, storage));
        }
    }

    public void run() {
        for (Chef chef : chefs) {
            chef.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    public void addDish(Dish dish) {
        this.storage.add(dish);
    }

    public void endOfDay() {
        this.endOfDay.set(true);
    }

    public void queueDish(Dish dish) {
        dish.setStatus("queued");
        System.out.println(dish.getStatus());
        this.queue.add(dish);
    }
}