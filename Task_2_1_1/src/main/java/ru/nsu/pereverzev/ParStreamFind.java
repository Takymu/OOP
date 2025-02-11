package ru.nsu.pereverzev;

import java.util.ArrayList;

public class ParStreamFind {
    public static boolean find(ArrayList<Integer> lst) {

        return lst.parallelStream().unordered().anyMatch(notSimple::check);
    }
}
