package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Collections;

public class GraphAdjMatr implements Graph {
    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<Boolean> validVerts;

    GraphAdjMatr() {
        validVerts = new ArrayList<Boolean>();
        matrix = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * adding new vertex with id = vnum.
     */
    @Override
    public void addVertex(int vnum) {
        if (matrix.size() <= vnum) // in this case we expand matrix to vnum x vnum size
        {
            int dif = vnum - matrix.size() + 1;
            validVerts.addAll(new ArrayList<Boolean>(Collections.nCopies(dif, false)));
            validVerts.set(vnum, true); // make the vertex valid
            for (int i = 0; i < dif; i++) {
                matrix.add(new ArrayList<Integer>(Collections.nCopies(vnum + 1, 0)));
            } // adding new rows
            for (int i = 0; i < matrix.size(); i++) {
                ArrayList<Integer> temp = matrix.get(i);
                temp.addAll(new ArrayList<Integer>(Collections.nCopies(dif, 0)));
                matrix.set(i, temp);
            } // adding new columns to old rows
        } else {
            validVerts.set(vnum, true);
        }
    }

    /**
     * removing vertex with id = vnum.
     */
    @Override
    public void removeVertex(int vnum) {
        for (int i = 0; i < matrix.size(); i++) {
            removeEdge(vnum, i);
            removeEdge(i, vnum);
        }
        validVerts.set(vnum, false);
    }

    /**
     * adding edge from start to end.
     */
    @Override
    public void addEdge(int start, int end) {
        ArrayList<Integer> temp = matrix.get(start);
        temp.set(end, 1); // or increment existing value? Depends of multigraph allowness
        matrix.set(start, temp);
    }

    /**
     * remove edge from start to end.
     */
    @Override
    public void removeEdge(int start, int end) {
        ArrayList<Integer> temp = matrix.get(start);
        temp.set(end, 0);
        matrix.set(start, temp);
    }

    /**
     * gets neibours of vertex with id = vnum.
     */
    @Override
    public ArrayList<Integer> getNeighbours(int vnum) {
        ArrayList<Integer> row = matrix.get(vnum);
        ArrayList<Integer> neibs = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            if (row.get(i) != 0) {
                neibs.add(i);
            }
        }
        return neibs;
    }

    @Override
    public int getVertexCount() {
        return matrix.size() - 1;
    }
}
