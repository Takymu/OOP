package ru.nsu.pereverzev;

import java.util.ArrayList;

public interface Graph {
    void addVertex(int vnum);
    void removeVertex(int vnum);
    void addEdge(int start, int end);
    void removeEdge(int start, int end);
    void readGraph(String filename);
    ArrayList<Integer> getNeighbours(int vnum);
}

