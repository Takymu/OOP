package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ParTest {

    public static ArrayList<Integer> generateNumlist(int count, int partPrimes) {
        ArrayList<Integer> primes = new ArrayList<>();
        int number = 1;
        Random r = new Random();
        while (primes.size() < count + partPrimes) {
            if (r.nextInt(count) < partPrimes) {
                if (notSimple.check(number)) {
                    primes.add(number);
                }
            } else {
                if (!notSimple.check(number)) {
                    primes.add(number);
                }
            }
            number++;
        }
        return primes;
    }

    @Test
    void find() {
        int[] masFalse = {7, 2, 5, 7, 13, 3, 3, 2};
        int[] masTrue = {7, 2, 6, 9, 4, 3, 3, 1, 8};
        ArrayList<Integer> arrTrue = new ArrayList<>();
        for (int i : masTrue) {
            arrTrue.add(i);
        }
        ArrayList<Integer> arrFalse = new ArrayList<>();
        for (int i : masFalse) {
            arrFalse.add(i);
        }

        assertTrue(MultyThreadFind.find(arrTrue, 4));
        assertTrue(ParStreamFind.find(arrTrue));

        assertFalse(MultyThreadFind.find(arrFalse, 5));
        assertFalse(ParStreamFind.find(arrFalse));

    }

    @Test
    void bigtest() {

        final int maxthreads = 16;
        final int precision = 100;
        ArrayList<Long> times = new ArrayList<>();
        for (int threadcnt = 1; threadcnt <= maxthreads; threadcnt++) {
            long dif = 0;
            for (int i = 0; i < precision; i++) {
                ArrayList<Integer> lst = generateNumlist(100000, 10);
                long startTime = System.nanoTime();
                MultyThreadFind.find(lst, threadcnt);
                dif += System.nanoTime() - startTime;
                System.out.print("iter ");
                System.out.println(i);
            }
            times.add(dif / precision);
        }
        System.out.println(times);
    }
}

