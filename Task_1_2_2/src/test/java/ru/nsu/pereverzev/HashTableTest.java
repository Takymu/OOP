package ru.nsu.pereverzev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

class HashTableTest {

    @Test
    void t1() {
        HashTable<String, Integer> table = new HashTable<String, Integer>();
        table.add("one", 1);
        table.add("ttwo", 22);
        table.add("two", 2);
        table.add("three", 3);
        table.add("five", 5);
        table.add("eleven", 11);
        Iterator<Pair<String, Integer>> iter = table.iterator();
        String s = table.toString();
        System.out.println(s);

    }

    @Test
    void t2() {
        HashTable<String, String> table = new HashTable<>();
        table.add("keyff", "val1f");
        assertEquals("{'keyff': val1f}", table.toString());
        table.add("newkey", "newvalue");
        table.remove("keyff", "val1f");
        assertEquals("{'newkey': newvalue}", table.toString());
        assertFalse(table.existValue("keyff"));
        assertTrue(table.existValue("newkey"));
        table.updateValue("newkey", "newnwv");
        assertEquals("newnwv", table.getValue("newkey"));
    }

    @Test
    void t3() {
        HashTable<String, String> t1 = new HashTable<>();
        HashTable<String, String> t2 = new HashTable<>();
        t1.add("ky1", "val1");
        t1.add("ky2", "valtwo");
        t2.add("ky1", "val1");
        t2.add("ky2", "valtwo");
        assertEquals(t1, t2);
        t1.updateValue("ky2", "valgwo");
        assertNotEquals(t1, t2);
    }

    @Test
    void t4concMod() {
        HashTable<String, Integer> table = new HashTable<String, Integer>();
        table.add("one", 1);
        table.add("ttwo", 22);
        table.add("two", 2);
        table.add("three", 3);
        table.add("five", 5);
        Iterator<Pair<String, Integer>> iter = table.iterator();
        iter.next();
        try {
            table.updateValue("three", 11);
        } catch (ConcurrentModificationException e) {
            System.out.print(e.getMessage());
            System.out.print('\n');
        }
        while (iter.hasNext()) {
            iter.next();
        }
        table.add("another", 3);
        System.out.print(table);

    }
}