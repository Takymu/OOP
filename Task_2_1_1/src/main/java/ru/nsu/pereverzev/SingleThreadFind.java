package ru.nsu.pereverzev;

import java.util.ArrayList;

/**
 * Класс для поиска непростых чисел в списке в однопоточном режиме.
 */
public class SingleThreadFind {
    /**
     * Метод для поиска непростого числа в списке в однопоточном режиме.
     */
    public static boolean find(ArrayList<Integer> lst) {
        for (int num : lst) {
            if (notSimple.check(num)) {
                return true;
            }
        }
        return false;
    }
}