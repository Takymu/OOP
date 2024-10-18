package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Collections;

public class GraphIncMatr implements Graph {
    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<Boolean> validVerts;
    @Override
    public void addVertex(int vnum) {
        if(vnum >= validVerts.size()) {
            int len = matrix.get(0).size();
            matrix.addAll(new ArrayList<ArrayList<Integer>>(
                    Collections.nCopies(vnum + 1 - len, new ArrayList<Integer>(
                            Collections.nCopies(len, 0))))); // adding new zero filled lines
            validVerts.addAll(new ArrayList<Boolean>(
                    Collections.nCopies(vnum + 1 - len, false)));
            
        }
    }

    @Override
    public void removeVertex(int vnum) {

    }

    @Override
    public void addEdge(int start, int end) {

    }

    @Override
    public void removeEdge(int start, int end) {

    }

    @Override
    public void readGraph(String filename) {

    }

    @Override
    public ArrayList<Integer> getNeighbours(int vnum) {
        return null;
    }

}
