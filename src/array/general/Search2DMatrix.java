package array.general;

public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        int maxcol = matrix[0].length - 1;
        int minrow = 0;

        while (maxcol >= 0 && minrow < matrix.length) {
            if (target > matrix[minrow][maxcol]) {
                minrow++;
            } else if (target < matrix[minrow][maxcol])
                maxcol--;
            else return true;
        }
        return false;
    }
}
