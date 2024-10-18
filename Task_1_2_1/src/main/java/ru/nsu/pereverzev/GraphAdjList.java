package ru.nsu.pereverzev;

import java.util.ArrayList;

public class GraphAdjList implements Graph {
    ArrayList<VertexAdLs> list;
    GraphAdjList() {
        list = new ArrayList<VertexAdLs>();
    }
    @Override
    public void addVertex(int vnum) {
        list.add(new VertexAdLs(vnum));
    }

    @Override
    public void removeVertex(int vnum) {
        int i = 0;
        while(i < list.size() && list.get(i).getId() != vnum) {
            i++;
            if(i == list.size())
                return;
        }
        list.remove(i);
        return;
    }

    @Override
    public void addEdge(int start, int end) {
        // TODO exception if no start or no end
        VertexAdLs vertStart = list.get(start);
        vertStart.listAdd(end);
        list.set(start, vertStart);
    }

    @Override
    public void removeEdge(int start, int end) {
        VertexAdLs vertStart = list.get(start);
        vertStart.listRemove(end);
        list.set(start, vertStart);
    }

    @Override
    public ArrayList<Integer> getNeighbours(int vnum) {
        return list.get(vnum).getNeighbs();
    }

    @Override
    public void readGraph(String filename) {
        //TODO read the graph
    }

}

class VertexAdLs {
    int vnum;
    ArrayList<Integer> adjlist;
    VertexAdLs(int vertNum) {
        vnum = vertNum;
        adjlist = new ArrayList<Integer>();
    }
    int getId() {return vnum;}
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
