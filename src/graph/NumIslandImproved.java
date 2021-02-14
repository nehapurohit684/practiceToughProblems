package graph;

public class NumIslandImproved {
    public static int numIslands(char[][] grid) {

        int numIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '0') {
                    dfs(i, j, grid);
                    numIsland++;
                }
            }
        }
        return numIsland;
    }

    private static void dfs(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i > grid.length || j > grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        if (i - 1 >= 0) dfs(i - 1, j, grid);
        if (i + 1 < grid.length) dfs(i + 1, j, grid);
        if (j - 1 >= 0) dfs(i, j - 1, grid);
        if (j + 1 < grid[0].length) dfs(i, j + 1, grid);
    }

    public static void main(String[] args) {
        char[][] input = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(input));
    }
}
