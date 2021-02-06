package dp;

import java.util.Arrays;

public class UniquePathsGrid {

    public static void main(String[] args) {

        int[][] question = {{0,0}};
        System.out.println(uniquePathsWithObstacles(question));

    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int[][] memo = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int[] row:memo){ Arrays.fill(row,0);}
        if(obstacleGrid[0][0] == 1) return 0;
        memo[0][0]=1;
        for(int i=1; i<obstacleGrid.length;i++){
            if(obstacleGrid[i][0]==1) break;
            memo[i][0]=1;
        }
        for(int j=1; j<obstacleGrid[0].length;j++){
            if(obstacleGrid[0][j]==1) break;
            memo[0][j]=1;
        }

        for(int i=1; i<obstacleGrid.length;i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] != 1)
                    memo[i][j] = memo[i-1][j]+memo[i][j-1];
                else
                    memo[i][j]=0;
            }
        }
        return memo[obstacleGrid.length-1][obstacleGrid[0].length-1];

    }
}
