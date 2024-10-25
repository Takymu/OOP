package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Collections;

/**
 * graph implementation thought incidence matrix.
 */
public class GraphIncMatr implements Graph {
    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<Boolean> validVerts;

    GraphIncMatr() {
        validVerts = new ArrayList<Boolean>();
        matrix = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * adding new vertex with id = vnum.
     */
    @Override
    public void addVertex(int vnum) {
        if (vnum >= validVerts.size()) {
            int len = 0;
            int matheight = matrix.size();
            if (!matrix.isEmpty()) {
                len = matrix.get(0).size();
                matrix.addAll(new ArrayList<ArrayList<Integer>>(Collections.nCopies(
                        vnum + 1 - matheight,
                        new ArrayList<Integer>(Collections.nCopies(len, 0))))
                ); // adding new zero filled lines
            } else {
                matrix.add(new ArrayList<Integer>());
                matrix.add(new ArrayList<Integer>());
            }
            validVerts.addAll(new ArrayList<Boolean>(
                    Collections.nCopies(vnum + 1 - matheight, false)));
            validVerts.set(vnum, true);
        } else {
            validVerts.set(vnum, true);
            ArrayList<Integer> row = matrix.get(vnum);
            for (int i = 0; i < row.size(); i++) {
                row.set(vnum, 0);
            }
        }
    }

    /**
     * removing vertex with id = vnum.
     */
    @Override
    public void removeVertex(int vnum) {
        validVerts.set(vnum, false);
    }

    /**
     * adding edge from start to end.
     */
    @Override
    public void addEdge(int start, int end) {
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Integer> row = matrix.get(i);
            if (i == start && start == end) {
                row.add(2); // it is a loop
            } else {
                if (i == start) {
                    row.add(1);
                } else if (i == end) {
                    row.add(-1);
                } else {
                    row.add(0);
                }
            }
        }
    }

    /**
     * remove edge from start to end.
     */
    @Override
    public void removeEdge(int start, int end) {
        ArrayList<Integer> startList = matrix.get(start);
        ArrayList<Integer> endList = matrix.get(end);
        int edgeid = 0;
        if (start == end) {
            for (int i = 0; i < startList.size(); i++) {
                if (startList.get(i) == 2) {
                    edgeid = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < startList.size(); i++) {
                if (startList.get(i) == 1 && endList.get(i) == -1) {
                    edgeid = i;
                    break;
                }
            }
        }
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Integer> temp = matrix.get(i);
            temp.remove(edgeid);
            matrix.set(i, temp);
        }
    }

    /**
     * gets neibours of vertex with id = vnum.
     */
    @Override
    public ArrayList<Integer> getNeighbours(int vnum) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ArrayList<Integer> vrow = matrix.get(vnum);
        for (int i = 0; i < vrow.size(); i++) {
            if (vrow.get(i) == 2) {
                ans.add(i);
            }
            if (vrow.get(i) == 1) {
                for (int r = 0; r < matrix.size(); r++) {
                    if (matrix.get(r).get(i) == -1) {
                        ans.add(r);
                    }
                }
            }
        }
        return ans;
    }

    @Override
    public int getVertexCount() {
        return matrix.size() - 1;
    }
}
