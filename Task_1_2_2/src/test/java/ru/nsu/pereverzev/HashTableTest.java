package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import java.net.Inet4Address;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void add() {
        HashTable<String, Integer> table = new HashTable<String, Integer>();
        table.add("one", 1);
        table.add("ttwo", 22);
        table.add("two", 2);
        table.add("three", 3);
        table.add("five", 5);
        //table.add("eleven", 11);
        Iterator<Pair<String, Integer>> iter = table.iterator();
        while (iter.hasNext()) {
            Pair<String, Integer> pair = iter.next();
            System.out.printf("key: %s, value: %d\n", pair.key, pair.value);
        }
    }

    @Test
    void remove() {
    }

    @Test
    void getValue() {
    }

    @Test
    void iterator() {
    }
}