package ru.nsu.pereverzev;

/**
 * simple class that is just key and value pair.
 */
public class Pair<K, V> {
    K key;
    V value;

    Pair(K keyn, V valn) {
        key = keyn;
        value = valn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Pair<K, V> thatPair = (Pair<K, V>) obj;
        return thatPair.getVal() == value && thatPair.getKey() == key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K newkey) {
        key = newkey;
    }

    public V getVal() {
        return value;
    }

    public void setVal(V newval) {
        value = newval;
    }
}
