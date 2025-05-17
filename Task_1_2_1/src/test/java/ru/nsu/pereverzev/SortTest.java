package ru.nsu.pereverzev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SortTest {

    @Test
    void tsortAdjacencyMatrix() {
        Graph graph = new GraphAdjMatr();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(1, 3);
        graph.removeEdge(1, 3);

        ArrayList<Integer> sorted = Utils.toposort(graph);
        int[] expect = new int[]{5, 4, 1, 2, 3};
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], sorted.get(i));
        }
    }

    @Test
    void tsortIncidenceMatrix() {
        Graph graph = new GraphIncMatr();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(1, 3);
        graph.removeEdge(1, 3);
        ArrayList<Integer> sorted = Utils.toposort(graph);
        int[] expect = new int[]{5, 4, 1, 2, 3};
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], sorted.get(i));
        }
    }

    @Test
    void tsortAdjacencyList() {
        Graph graph = new GraphAdjList();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(1, 3);
        graph.removeEdge(1, 3);
        ArrayList<Integer> sorted = Utils.toposort(graph);
        int[] expect = new int[]{5, 4, 1, 2, 3};
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], sorted.get(i));
        }
    }

    @Test
    void tsortCycleFinding() {
        Graph graph = new GraphAdjList();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        try {
            Utils.toposort(graph);
        } catch (graphException ec) {
            assertEquals(ec.getMessage(), "there is cycle in graph");
        }
        try {
            graph.addVertex(1);
        } catch (graphException ec) {
            return;
        }
    }
}