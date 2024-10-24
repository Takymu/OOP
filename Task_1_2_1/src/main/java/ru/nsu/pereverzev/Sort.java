package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    static ArrayList<Integer> stack;
    static ArrayList<Boolean> visited;
    public static ArrayList<Integer> toposort(Graph graph) {
        if (graph == null) {
            // TODO exception
        }
        int vertcnt = graph.getVertexCount();
        stack = new ArrayList<Integer>();
        visited = new ArrayList<Boolean>(Collections.nCopies(vertcnt+1, false));
        for(int i = 1; i <= vertcnt; i++) {
            if(!visited.get(i))
                dfstoposort(graph, i);
        }
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for(int i = stack.size() - 1; i >= 0; i--) {
            answer.add(stack.get(i));
        }
        return answer;
    }
    private static void dfstoposort(Graph graph, int curvert) {
        visited.add(curvert, true);
        ArrayList<Integer> neigbs = graph.getNeighbours(curvert);
        for (int i = 0; i < neigbs.size(); i++) {
            if (!visited.get(neigbs.get(i))) {
                dfstoposort(graph, neigbs.get(i));
            }
        }
        stack.add(curvert);
    }
}
