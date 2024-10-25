package ru.nsu.pereverzev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * class with utilities, such as sort or reading from file.
 */
public class Utils {

    /**
     * read the graph from file, that contains graph in adjacency list format.
     */
    public static Graph readFromFile(String filename, Graph graph) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.print(e.getMessage());
            return null;
        }
        while (scanner.hasNextLine()) {
            String inps = scanner.nextLine();
            String[] strnums = inps.split("\\s+");
            int[] nums = new int[strnums.length];
            for (int i = 0; i < strnums.length; i++) {
                nums[i] = Integer.parseInt(strnums[i]);
            }
            int start = nums[0];
            graph.addVertex(start);
            for (int i = 1; i < nums.length; i++) {
                graph.addEdge(start, nums[i]);
            }
        }
        scanner.close();
        return graph;
    }

    /**
     * topologic sort of the graph, independently of the graph format.
     */
    public static ArrayList<Integer> toposort(Graph graph) {
        if (graph == null) {
            throw new graphException("graph is null, nothing to sort!");
        }
        int vertcnt = graph.getVertexCount();
        ArrayList<Integer> stack = new ArrayList<Integer>();
        ArrayList<Boolean> visited = new ArrayList<Boolean>(Collections.nCopies(vertcnt + 1, false));
        for (int i = 1; i <= vertcnt; i++) {
            if (!visited.get(i)){
                dfstoposort(graph, i, stack, visited);
            }
        }
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for (int i = stack.size() - 1; i >= 0; i--) {
            answer.add(stack.get(i));
        }
        return answer;
    }

    private static void dfstoposort(Graph graph, int curvert,
                                    ArrayList<Integer> stack, ArrayList<Boolean> visited) {
        visited.add(curvert, true);
        ArrayList<Integer> neigbs = graph.getNeighbours(curvert);
        for (int i = 0; i < neigbs.size(); i++) {
            if (!visited.get(neigbs.get(i))) {
                dfstoposort(graph, neigbs.get(i), stack, visited);
            }
        }
        if(stack.contains(curvert)) {
            throw new graphException("there is cycle in graph");
        }
        stack.add(curvert);
    }
}
