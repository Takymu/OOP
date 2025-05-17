package ru.nsu.pereverzev;

import java.util.ArrayList;

/**
 * A thread-safe queue implementation.
 */
public class QueueSafe<T> {

    private final ArrayList<T> queue;

    /**
     * Constructs a QueueSafe with the specified size.
     */
    QueueSafe(int size) {
        this.queue = new ArrayList<T>(size);
    }

    /**
     * Adds an item to the queue.
     */
    public synchronized void add(T item) {
        this.queue.add(item);
    }

    /**
     * Removes and returns the first item in the queue, or null if empty.
     */
    public synchronized T take() {
        if (this.queue.isEmpty()) {
            return null;
        }
        return this.queue.remove(0);
    }

    /**
     * Checks if the queue is empty.
     */
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
