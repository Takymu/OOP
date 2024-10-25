package ru.nsu.pereverzev;

import java.util.ArrayList;


/**
 * the main interface, that represents graph.
 */
public interface Graph {
    void addVertex(int vnum);

    void removeVertex(int vnum);

    void addEdge(int start, int end);

    void removeEdge(int start, int end);

    ArrayList<Integer> getNeighbours(int vnum);

    int getVertexCount();
}

