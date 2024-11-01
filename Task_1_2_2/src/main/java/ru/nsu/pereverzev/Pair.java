package ru.nsu.pereverzev;

public class Pair<K, V> {
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
