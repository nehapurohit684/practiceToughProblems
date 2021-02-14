package graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumOfIsland {


    public static int numIslands(char[][] grid) {

        int numIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '0') {
                    helperBFS(i, j, grid);
                    numIsland++;
                }
            }
        }
        return numIsland;
    }

    private static void helperBFS(int i, int j, char[][] grid) {

        Queue<Pair<Integer, Integer>> queue = new LinkedList();
        queue.add(new Pair<>(i, j));
        grid[i][j] = '0';
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();
            for (Pair<Integer, Integer> neighbour : getNeighbours(node.getKey(), node.getValue(), grid)) {
                if (grid[neighbour.getKey()][neighbour.getValue()] != '0') {
                    grid[neighbour.getKey()][neighbour.getValue()] = '0';
                    queue.add(neighbour);
                }
            }
        }

    }

    private static List<Pair<Integer, Integer>> getNeighbours(Integer x, Integer y, char[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return new ArrayList<>();
        List<Pair<Integer, Integer>> results = new ArrayList<>();
        if (x - 1 >= 0) results.add(new Pair<>(x - 1, y));
        if (x + 1 < grid.length) results.add(new Pair<>(x + 1, y));
        if (y - 1 >= 0) results.add(new Pair<>(x, y - 1));
        if (y + 1 < grid[0].length) results.add(new Pair<>(x, y + 1));
        return results;
    }

    public static void main(String[] args) {
        char[][] input = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(input));
    }
}



