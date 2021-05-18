package dp;

public class MinPathSumMatrix {
    /**
     * 64. Minimum Path Sum
     * DP for DAG - Min max path or number of paths, But BFS for normal graph
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        memo[0][0] = grid[0][0];

        for (int i = 1; i < grid.length; i++) {
            memo[i][0] = memo[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            memo[0][i] = memo[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                memo[i][j] = grid[i][j] + Math.min(memo[i][j - 1], memo[i - 1][j]);
            }
        }
        return memo[grid.length - 1][grid[0].length - 1];
    }
}
