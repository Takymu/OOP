package ru.nsu.pereverzev;

import java.util.concurrent.atomic.AtomicBoolean;

public class Chef extends Thread{
    private double speed;
    private AtomicBoolean endOfDay;
    private QueueSafe<Dish> storage;
    private QueueSafe<Dish> queue;
    Chef(double speed, AtomicBoolean endOfDay,
            QueueSafe<Dish> storage, QueueSafe<Dish> queue) {
        this.speed = speed;
        this.endOfDay = endOfDay;
        this.storage = storage;
        this.queue = queue;
    }
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
