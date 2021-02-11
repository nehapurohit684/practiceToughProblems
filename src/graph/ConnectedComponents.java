package graph;

import java.util.*;

public class ConnectedComponents {

    public static int countComponents(int n, int[][] edges) {
        //Build the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int connectedComponent = 0;
        //outer loop
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                helperBFS(i, visited, graph);
                helperDFS(i, visited, graph);
                connectedComponent++;
            }
        }
        return connectedComponent;
    }

    private static void helperDFS(int i, boolean[] visited, Map<Integer, List<Integer>> graph) {
        visited[i] = true;
        for (int j : graph.get(i)) {
            if (!visited[j]) helperDFS(j, visited, graph);
        }
    }

    private static void helperBFS(int i, boolean[] visited, Map<Integer, List<Integer>> graph) {

        Queue<Integer> queue = new LinkedList();
        visited[i] = true;
        queue.add(i);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int j : graph.get(current)) {
                if (!visited[j]) {
                    visited[j] = true;
                    queue.add(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(countComponents(5, edges));
    }
}
