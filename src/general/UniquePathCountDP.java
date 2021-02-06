package general;

import java.util.Arrays;
import java.util.List;

public class UniquePathCountDP {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

    int table[][] = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int[] row: table)
        Arrays.fill(row,0);

        if(obstacleGrid[0][0]==1) return 0;
        table[0][0]=1;
        for (int i = 1; i < obstacleGrid.length; i++) {
            if(obstacleGrid[i][0]==1) break;
            else table[0][i] = 1;
        }
        for (int i = 1; i < obstacleGrid[0].length; i++) {
            if(obstacleGrid[0][i]==1) break;
            else table[i][0] = 1;
        }

        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if(obstacleGrid[i][j]==1) table[i][j]=0;
                else table[i][j] = table[i-1][j]+table[i][j-1];
            }
        }
        return table[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }


    public static int numberOfPaths(List<List<Integer>> matrix) {
        // Write your code here
        if(matrix.get(0).get(0)==0) return 0;
        int[][] table = new int[matrix.size()][matrix.get(0).size()];
        for (int[] row: table) Arrays.fill(row,0);
        table[0][0]=1;
        for (int i = 1; i < matrix.get(0).size(); i++) {
            if(matrix.get(0).get(i)==0) break;
            table[0][i] = 1;
        }
        for (int j = 1; j < matrix.size(); j++) {
            if(matrix.get(j).get(0)==0) break;
            table[j][0] = 1;
        }

        for (int i = 1; i <  matrix.size() ; i++) {
            for (int j = 1; j < matrix.get(0).size(); j++) {
                if(matrix.get(i).get(j)==0) table[i][j] = 0;
                else table[i][j] = table[i-1][j]+table[i][j-1];
            }
        }

        return table[matrix.size()-1][matrix.get(0).size()-1];

    }
}


