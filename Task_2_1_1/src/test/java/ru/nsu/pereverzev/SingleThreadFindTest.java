package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SingleThreadFindTest {

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

        assertTrue(SingleThreadFind.find(arrTrue));

        assertFalse(SingleThreadFind.find(arrFalse));
    }
}