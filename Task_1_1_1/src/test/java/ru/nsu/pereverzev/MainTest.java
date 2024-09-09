package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static ru.nsu.pereverzev.Main.heapsort;

class MainTest {
    @Test
    void SampleTest() {
        int[] arr = {1, 5, 1, 2, 9, 4, 7, 6, 3, 2};
        heapsort(arr);
        int len = arr.length;
        int[] sorted = {1, 1, 2, 2, 3, 4, 5, 6, 7, 9};
        for(int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], sorted[i]);
        }
    }
}