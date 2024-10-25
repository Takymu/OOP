package ru.nsu.pereverzev;

import java.util.ArrayList;

/**
 * this class implements graph interface using adjacency list.
 */
public class GraphAdjList implements Graph {
    ArrayList<VertexAdLs> list;

    GraphAdjList() {
        list = new ArrayList<VertexAdLs>();
    }

    @Override
    public void addVertex(int vnum) {
        list.add(new VertexAdLs(vnum));
    }

    /**
     * method for vertex removing, find vertex with id = vnum and removes it.
     */
    @Override
    public void removeVertex(int vnum) {
        int i = 0;
        while (i < list.size() && list.get(i).getId() != vnum) {
            i++;
            if (i == list.size()) {
                return;
            }
        }
        list.remove(i);
    }

    /**
     * method for edge adding. Finds vertex with id = start and adds end to it's list.
     */
    @Override
    public void addEdge(int start, int end) {
        // TODO exception if no start or no end
        int idstart = 0;
        int idend = 0;
        for (int i = 0; i < list.size(); i++) {
            VertexAdLs vert = list.get(i);
            if (vert.getId() == start) {
                idstart = i;
                break;
            }
        }
        VertexAdLs vertStart = list.get(idstart);
        vertStart.listAdd(end);
        list.set(idstart, vertStart);
    }

    /**
     * method for edge removing. Same as addEdge, but removes vertex from list.
     */
    @Override
    public void removeEdge(int start, int end) {
        VertexAdLs vertStart;
        for (int i = 0; i < list.size(); i++) {
            vertStart = list.get(i);
            if (vertStart.getId() == start) {
                vertStart.listRemove(end);
                //list.set(start, vertStart);
                break;
            }
        }
    }

    /**
     * method returns list of neighbours of the vertex, pointed by vnum.
     */
    @Override
    public ArrayList<Integer> getNeighbours(int vnum) {
        VertexAdLs vert;
        for (VertexAdLs v : list) {
            vert = v;
            if (vert.getId() == vnum) {
                return vert.getNeighbs();
            }
        }
        return null;
    }

    @Override
    public int getVertexCount() {
        return list.size();
    }

    /**
     * vertex, one element of adjacency list (with it's own list with connected verts).
     */
    static private class VertexAdLs {
        int vnum;
        ArrayList<Integer> adjlist;

        VertexAdLs(int vertNum) {
            vnum = vertNum;
            adjlist = new ArrayList<Integer>();
        }

        int getId() {
            return vnum;
        }

        void listAdd(int vertNum) {
            adjlist.add(vertNum);
        }

        void listRemove(int vertNum) {
            int i = 0;
            while (adjlist.get(i) != vertNum) {
                i++;
            }
            adjlist.remove(i);
        }

        ArrayList<Integer> getNeighbs() {
            return adjlist;
        }

    }
}


