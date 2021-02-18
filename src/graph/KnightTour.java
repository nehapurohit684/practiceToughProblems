package graph;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class KnightTour {

    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {
        int distance = 0;
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        Queue<Pair<Integer, Integer>> pairList = new LinkedList<>();
        pairList.add(new Pair<>(start_row, start_col));
        visited[start_row][start_col] = true;
        while (!pairList.isEmpty()) {
            int size = pairList.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> current = pairList.poll();
                if (current.getKey() == end_row && current.getValue() == end_col) return distance;
                for (int[] d : directions) {
                    int newX = current.getKey() + d[0];
                    int newY = current.getValue() + d[1];
                    if ((newX >= 0 && newY >= 0) && (newX < rows && newY < cols)) {
                        if (!visited[newX][newY]) {
                            visited[newX][newY] = true;
                            pairList.add(new Pair<>(newX, newY));
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}