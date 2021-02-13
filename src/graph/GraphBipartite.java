package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBipartite {

    public static boolean isBipartite(int[][] graph) {

        int[] level = new int[graph.length];
        Arrays.fill(level, -1);
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(visited, false);

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) if (!helperBFS(i, level, visited, graph)) return false;
        }
        return true;
    }

    private static boolean helperBFS(int node, int[] level, boolean[] visited, int[][] graph) {
        visited[node] = true;
        level[node] = 0;
        Queue<Integer> list = new LinkedList<>();
        list.add(node);

        while (!list.isEmpty()) {
            int temp = list.poll();
            for (int j : graph[temp]) {
                if (!visited[j]) {
                    visited[j] = true;
                    level[j] = level[temp] + 1;
                    list.add(j);
                } else {
                    if (level[j] == level[temp]) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = {{2, 1}, {0, 2}, {1, 3}, {1, 4}};
        System.out.println(isBipartite(edges));
    }

}
