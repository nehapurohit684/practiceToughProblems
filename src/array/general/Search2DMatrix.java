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

    /**
     * Leetcode 277
     * Hint: In first pass find the the number to whom more ppl know
     * in second pass check if this number know anyone if no then he is celebrity.
     *
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        int maxCol = n - 1;
        int minRow = 0;
        int celebrityCandidate = 0;

        for (int i = 0; i < n; i++) {
//            if(knows(celebrityCandidate,i)){
//                celebrityCandidate = i;
//            }
        }
        if (isCelebrity(celebrityCandidate, n)) {
            return celebrityCandidate;
        }
        return -1;
    }

    boolean isCelebrity(int i, int n) {
        for (int j = 0; j < n; j++) {
            if (i == j) continue;
//            if(knows(i,j) || !knows(j,i)) return false;
        }
        return true;
    }


}
