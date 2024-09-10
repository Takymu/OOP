package ru.nsu.pereverzev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static ru.nsu.pereverzev.Main.heapsort;

import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void heapsort_test() {
        int[] arr = {1, 5, 1, 2, 9, 4, 7, 6, 3, 2};
        heapsort(arr);
        int len = arr.length;
        int[] sorted = {1, 1, 2, 2, 3, 4, 5, 6, 7, 9};
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], sorted[i]);
        }
    }
}