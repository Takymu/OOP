package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReadTest {

    @Test
    void readFromFileTest() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("./graph.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.print(e.getMessage());
            return;
        }
        writer.println("1 2");
        writer.println("2 3");
        writer.println("3");
        writer.println("4 1");
        writer.println("5");
        writer.close();
        GraphAdjMatr graphadj = new GraphAdjMatr();
        Graph graph = Utils.readFromFile("./graph.txt", graphadj);
        assertNotEquals(graph, null);

        ArrayList<Integer> sorted = Utils.toposort(graph);
        int[] expect = new int[]{5,4,1,2,3};
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], sorted.get(i));
        }
    }
}