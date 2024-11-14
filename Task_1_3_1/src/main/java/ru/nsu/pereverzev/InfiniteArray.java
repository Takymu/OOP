package ru.nsu.pereverzev;

/**
 * simple semi-infinite array that is allowed to index by very big values.
 */
public class InfiniteArray {
    byte[] arr;
    int size;

    InfiniteArray(int arsize) {
        size = arsize;
        arr = new byte[size];
    }

    public void set(long id, byte elem) {
        arr[(int) (id % ((long) size))] = elem;
    }

    public byte get(long id) {
        return arr[(int) (id % ((long) size))];
    }
}
