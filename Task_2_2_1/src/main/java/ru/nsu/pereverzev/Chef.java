package ru.nsu.pereverzev;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Chef extends Thread{
    private double speed;
    private AtomicBoolean endOfDay;
    private ArrayBlockingQueue<Dish> storage;
    private LinkedBlockingQueue<Dish> queue;
    Chef(double speed, AtomicBoolean endOfDay,
         ArrayBlockingQueue<Dish> storage, LinkedBlockingQueue<Dish> queue) {
        this.speed = speed;
        this.endOfDay = endOfDay;
        this.storage = storage;
        this.queue = queue;
    }
    public void run() {
        try {
            while (!endOfDay.get()) {
                Dish dish = queue.take();
                Thread.sleep((long) (1000 / speed));
                dish.setStatus("cooked");
                System.out.println(dish.getStatus());
                storage.put(dish);
            }            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
