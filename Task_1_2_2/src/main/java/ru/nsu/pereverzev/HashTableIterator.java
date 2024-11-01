package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTableIterator<K, V> implements Iterator<Pair<K, V>> {
    ArrayList<LinkedList<Pair<K, V>>> table;
    int curBuckId;
    LinkedList<Pair<K, V>> curList;
    Iterator<Pair<K, V>> curBuckIter;
    HashTableIterator(ArrayList<LinkedList<Pair<K, V>>> thisTable){
        curBuckId = 0;
        table = thisTable;
        curList = table.get(curBuckId);
        while((curBuckId < table.size() - 1) && curList == null) {
            curBuckId++;
            curList = table.get(curBuckId);
        }
        curBuckIter = curList.iterator();
    }
    public boolean hasNext() {
        if (curBuckIter.hasNext()) {
            return true;
        } else {
            while (curBuckId < table.size()) {
                curBuckId++;
                curList = table.get(curBuckId);
                if(curList == null) {
                    continue;
                }
                if(!curList.isEmpty()) {
                    break;
                }
            }
            return curBuckId != table.size();
        }
    }
    public Pair<K, V> next() {
        if(!hasNext())
            return null;
        if(curBuckIter.hasNext()) {
            return curBuckIter.next();
        } else {
            curBuckId++;
            while(curBuckId < table.size() &&
                    (table.get(curBuckId) == null || !curBuckIter.hasNext())) {
                curBuckIter = table.get(curBuckId).iterator();
                curBuckId++;
            }
            if(curBuckId == table.size()) {
                return null;
            } else {
                return curBuckIter.next();
            }
        }
    }
}
