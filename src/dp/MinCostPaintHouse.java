package dp;

public class MinCostPaintHouse {

    public static int minCost(int[][] costs) {

        if (costs.length == 0) return 0;

        int[][] memo = new int[costs.length][costs[0].length];

        for (int i = 0; i < 3; i++) {
            memo[0][i] = costs[0][i];
        }

        for (int j = 1; j < costs.length; j++) {
            for (int i = 0; i < 3; i++) {
                memo[j][i] = costs[j][i] + Math.min(memo[j - 1][(i + 1) % 3], memo[j - 1][(i + 2) % 3]);
            }
        }
        return findMin(memo[costs.length - 1]);
    }

    private static int findMin(int[] ints) {
        int min = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (min > ints[i]) min = ints[i];
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] costs = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        System.out.println(minCost(costs));
    }
}
