package ru.nsu.pereverzev;

import java.util.ArrayList;

public class QueueSafe<T> {
    
    private ArrayList<T> queue;

    QueueSafe(int size) {
        this.queue = new ArrayList<T>(size);
    }

    synchronized public void add(T item) {
        this.queue.add(item);   
    }

    synchronized public T take() {
        if (this.queue.isEmpty()) {
            return null;
        }
        return this.queue.remove(0);
    }

    synchronized public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
