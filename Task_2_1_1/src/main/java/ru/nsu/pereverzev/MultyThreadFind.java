package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс для многопоточного поиска непростых чисел в списке.
 */
public class MultyThreadFind {
    /**
     * Метод для многопоточного поиска непростого числа в списке.
     */
    public static boolean find(ArrayList<Integer> lst, int thrnum) {

        AtomicBoolean found = new AtomicBoolean(false);
        thrnum = Math.min(thrnum, lst.size());
        Thread[] threads = new Thread[thrnum];

        int len = lst.size() / thrnum;
        for (int i = 0; i < thrnum; i++) {
            int sid = i * len;
            int end = Math.min(sid + len, lst.size());
            threads[i] = new Thread(() -> {
                for (int j = sid; j < end; j++) {
                    if (found.get()) {
                        return;
                    }
                    if (notSimple.check(lst.get(j))) {
                        found.set(true);
                        break;
                    }
                }
            });
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return found.get();
    }
}