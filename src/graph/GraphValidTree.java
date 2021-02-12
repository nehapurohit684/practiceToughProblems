package graph;

import java.util.*;

public class GraphValidTree {
    public static boolean validTree(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        int parent[] = new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);


        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);

        }
        int connected = 0;
        boolean hasCycle;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                hasCycle = helperDFS(i, graph, visited, parent);
                connected++;
                if (connected > 1 || hasCycle) return false;
            }
        }
        return true;
    }


    private static boolean helperDFS(int i, Map<Integer, List<Integer>> graph, boolean[] visited, int[] parent) {

        visited[i] = true;
        for (int j : graph.get(i)) {
            if (!visited[j]) {
                visited[j] = true;
                parent[j] = i;
                if (helperDFS(j, graph, visited, parent)) return true;
            } else {
                if (j != parent[i]) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(validTree(5, edges));
    }
}
