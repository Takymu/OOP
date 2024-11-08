package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * implementation of iterator for the hash table.
 */
public class HashTableIterator<K, V> implements Iterator<Pair<K, V>> {
    ArrayList<LinkedList<Pair<K, V>>> table;
    int curBuckId;
    HashTable<K, V> hashTable;
    int savedModCount;
    LinkedList<Pair<K, V>> curList;
    Iterator<Pair<K, V>> curBuckIter;

    HashTableIterator(ArrayList<LinkedList<Pair<K, V>>> thisTable,
                      HashTable<K, V> htable) {
        curBuckId = 0;
        table = thisTable;
        hashTable = htable;
        savedModCount = hashTable.getModCount();
        curList = table.get(curBuckId);
        while ((curBuckId < table.size() - 1) && curList == null) {
            curBuckId++;
            curList = table.get(curBuckId);
        }
        curBuckIter = curList.iterator();
    }

    /**
     * check if table has the next element.
     */
    public boolean hasNext() {
        if(hashTable.getModCount() != savedModCount) {
            throw new ConcurrentModificationException("hash table was modified");
        }
        if (curBuckIter.hasNext()) {
            return true;
        } else {
            while (curBuckId < table.size() - 1) {
                curBuckId++;
                curList = table.get(curBuckId);
                if (curList == null) {
                    continue;
                }
                if (!curList.isEmpty()) {
                    break;
                }
            }

            if (curBuckId != table.size() - 1) {
                curBuckIter = table.get(curBuckId).iterator();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * switching to the next element.
     */
    public Pair<K, V> next() {
        if (!hasNext()) {
            return null;
        }
        return curBuckIter.next();
    }
}
