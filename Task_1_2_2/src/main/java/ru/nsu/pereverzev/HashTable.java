package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class HashTable <K,V> {
    int elcnt;
    ArrayList<LinkedList<Pair>> table;
    HashTable() {
        elcnt = 0;
        table = new ArrayList<>();
    }
    public void add(K key, V value) {
        elcnt++;
        if(elcnt > table.size() / 2) {
            ArrayList<LinkedList<Pair>> newTable = new ArrayList<>(table.size() * 2);
            // TODO iterate by the old table, recalculate hashes of all
            //      the keys and write key-val pairs into new table
        }
        int id = key.hashCode() % table.size();
        LinkedList<Pair> bucket = table.get(id);
        if (bucket == null) {
            LinkedList<Pair> list = new LinkedList<>();
            list.add(new Pair(key, value));
            table.set(id, list);
        } else {
            bucket.add(new Pair(key, value));
        }
    }

    public void remove(K key, V value) {
        int id = key.hashCode() % table.size();
        LinkedList<Pair> bucket = table.get(id);
        if (bucket == null) {
            throw new HashTableException("no such element to remove from table");
        } else {
            Pair pair = new Pair(key, value);
            if(bucket.contains(pair)){
                bucket.removeLastOccurrence(pair);
            } else {
                throw new HashTableException("no such element to remove from table");
            }
        }
    }

    public V getValue(K key) {
        int id = key.hashCode() % table.size();
        if (table.get(id) == null) {
            return null;
        } else {
            for (Pair pair : table.get(id)) {
                if (pair.getKey() == key) {
                    return pair.getVal();
                }
            }
            return null;
        }
    }

    public void updateValue(K key, V value) {

    }

    class Pair {
        K key;
        V value;
        Pair(K keyn, V valn) {
            key = keyn;
            value = valn;
        }
        public void setKey(K newkey) {
            key = newkey;
        }
        public void setVal(V newval) {
            value = newval;
        }
        public K getKey() {
            return key;
        }
        public V getVal() {
            return value;
        }
    }

}
