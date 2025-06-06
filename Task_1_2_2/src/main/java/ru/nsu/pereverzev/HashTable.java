package ru.nsu.pereverzev;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * hash table class.
 */
public class HashTable<K, V> implements Iterable<Pair<K, V>> {
    private int elcnt;
    private int modcnt;
    private ArrayList<LinkedList<Pair<K, V>>> table;

    HashTable() {
        elcnt = 0;
        modcnt = 0;
        table = new ArrayList<>(Collections.nCopies(4, null));
    }

    public int getModCount() {
        return modcnt;
    }

    /**
     * adding new element to the hash table.
     */
    public void add(K key, V value) {
        elcnt++;
        modcnt++;
        if (elcnt > table.size() / 2) {
            ArrayList<LinkedList<Pair<K, V>>> newTable = new ArrayList<>(Collections.nCopies(table.size() * 2, null));
            for (Pair<K, V> pair : this) {
                int id = pair.getKey().hashCode() % newTable.size();
                LinkedList<Pair<K, V>> bucket = newTable.get(id);
                if (bucket == null) {
                    LinkedList<Pair<K, V>> list = new LinkedList<>();
                    list.add(pair);
                    newTable.set(id, list);
                } else {
                    bucket.add(pair);
                }
            }
            table = newTable;
        }
        int id = abs(abs(key.hashCode())) % table.size();
        LinkedList<Pair<K, V>> bucket = table.get(id);
        if (bucket == null) {
            LinkedList<Pair<K, V>> list = new LinkedList<>();
            list.add(new Pair<K, V>(key, value));
            table.set(id, list);
        } else {
            bucket.add(new Pair<K, V>(key, value));
        }
    }

    /**
     * remove element by key and value.
     */
    public void remove(K key, V value) {
        modcnt++;
        int id = abs(abs(key.hashCode())) % table.size();
        LinkedList<Pair<K, V>> bucket = table.get(id);
        if (bucket == null) {
            throw new HashTableException("no such element to remove from table");
        } else {
            Pair<K, V> pair = new Pair<K, V>(key, value);
            if (bucket.contains(pair)) {
                bucket.removeLastOccurrence(pair);
            } else {
                throw new HashTableException("no such element to remove from table");
            }
        }
    }

    /**
     * get value by the key.
     */
    public V getValue(K key) {
        int id = abs(key.hashCode()) % table.size();
        if (table.get(id) == null) {
            return null;
        } else {
            for (Pair<K, V> pair : table.get(id)) {
                if (pair.getKey() == key) {
                    return pair.getVal();
                }
            }
            return null;
        }
    }

    /**
     * update value founded by the key.
     */
    public void updateValue(K key, V value) {
        modcnt++;
        int id = abs(key.hashCode()) % table.size();
        List<Pair<K, V>> bucket = table.get(id);
        if (bucket == null) {
            throw new HashTableException("no value to this key");
        } else {
            Pair<K, V> pair = null;
            for (int i = 0; i < bucket.size(); i++) {
                pair = bucket.get(i);
                if (pair.getKey() == key) {
                    pair.setVal(value);
                    bucket.set(i, pair);
                    break;
                }
            }
        }
    }

    /**
     * returns true if there exist value with this key in table.
     */
    public boolean existValue(K key) {
        int id = abs(key.hashCode()) % table.size();
        List<Pair<K, V>> bucket = table.get(id);
        if (bucket == null) {
            return false;
        } else {
            for (Pair<K, V> pair : table.get(id)) {
                if (pair.getKey() == key) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new HashTableIterator<>(table, this);
    }

    /**
     * method that converts table to string view.
     */
    public String toString() {
        String str = "{";
        int cnt = 0;
        for (Pair<K, V> pair : this) {
            if (cnt == 1) {
                str = str.concat(", ");
            }
            str = str.concat("'" + pair.getKey().toString() + "': " + pair.getVal().toString());
            cnt = 1;
        }
        str = str.concat("}");
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Iterator<Pair<K, V>> iter = iterator();
        HashTable<K, V> tableThat = (HashTable<K, V>) obj;
        while (iter.hasNext()) {
            Pair<K, V> pair = iter.next();
            if (!tableThat.existValue(pair.getKey())) {
                return false;
            }
            if (tableThat.getValue(pair.getKey()) != pair.value) {
                return false;
            }
        }
        iter = tableThat.iterator();
        while (iter.hasNext()) {
            Pair<K, V> pair = iter.next();
            if (!this.existValue(pair.getKey())) {
                return false;
            }
            if (this.getValue(pair.getKey()) != pair.value) {
                return false;
            }
        }
        return true;
    }
}
