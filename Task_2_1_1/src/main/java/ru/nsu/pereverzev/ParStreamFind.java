package ru.nsu.pereverzev;

import java.util.ArrayList;

/**
 * Класс для поиска непростых чисел в списке с использованием parrallelStream.
 */
public class ParStreamFind {
    /**
     * Метод для поиска непростого числа в списке с использованием parrallelStream.
     */
    public static boolean find(ArrayList<Integer> lst) {

        return lst.parallelStream().unordered().anyMatch(notSimple::check);
    }
}