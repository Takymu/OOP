package ru.nsu.pereverzev;

import java.util.*;

public class HashTable <K,V> implements Iterable<Pair<K, V>>{
    int elcnt;
    ArrayList<LinkedList<Pair<K, V>>> table;
    HashTable() {
        elcnt = 0;
        table = new ArrayList<>(Collections.nCopies(4, null));
    }
    public void add(K key, V value) {
        elcnt++;
        if(elcnt > table.size() / 2) {
            ArrayList<LinkedList<Pair<K, V>>> newTable = new ArrayList<>(
                    Collections.nCopies(table.size() * 2, null));
            Iterator<Pair<K, V>> iter = iterator();
            while (iter.hasNext()) {
                Pair<K, V> pair = iter.next();
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
        int id = key.hashCode() % table.size();
        LinkedList<Pair<K, V>> bucket = table.get(id);
        if (bucket == null) {
            LinkedList<Pair<K, V>> list = new LinkedList<>();
            list.add(new Pair<K, V>(key, value));
            table.set(id, list);
        } else {
            bucket.add(new Pair<K, V>(key, value));
        }
    }

    public void remove(K key, V value) {
        int id = key.hashCode() % table.size();
        LinkedList<Pair<K, V>> bucket = table.get(id);
        if (bucket == null) {
            throw new HashTableException("no such element to remove from table");
        } else {
            Pair<K, V> pair = new Pair<K, V>(key, value);
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
            for (Pair<K, V> pair : table.get(id)) {
                if (pair.getKey() == key) {
                    return pair.getVal();
                }
            }
            return null;
        }
    }

    public void updateValue(K key, V value) {

    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new HashTableIterator<>(table);
    }
}
