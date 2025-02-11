package ru.nsu.pereverzev;

import java.util.ArrayList;

public class SingleThreadFind {
    public static boolean find(ArrayList<Integer> lst) {
        for (int num : lst) {
            if (notSimple.check(num)) {
                return true;
            }
        }
        return false;
    }
}
