package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class HashTableIterator<K, V> implements Iterator<HashTable<K, V>> {
    ArrayList<LinkedList<HashTable<K, V>.Pair>> table;
    int curBuckId;
    LinkedList<HashTable<K, V>.Pair> curList;
    Iterator<HashTable<K, V>.Pair> curBuckIter;
    HashTableIterator(ArrayList<LinkedList<HashTable<K, V>.Pair>> thisTable){
        curBuckId = 0;
        table = thisTable;
        curList = table.get(curBuckId);
        while(curBuckId < table.size() && curList == null) {
            curBuckId++;
            curList = table.get(curBuckId);
        }
        curBuckIter = curList.iterator();
    }
    public boolean hasNext(){
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
}
