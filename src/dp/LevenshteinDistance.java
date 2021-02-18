package dp;

import java.util.Arrays;

public class LevenshteinDistance {

    static int levenshteinDistance(String strWord1, String strWord2) {

        int[][] minEdit = new int[strWord1.length() + 1][strWord2.length() + 1];
        for (int[] array : minEdit) {
            Arrays.fill(array, -1);
        }
        minEdit[0][0] = 0;
        for (int i = 1; i < strWord1.length() + 1; i++) {
            minEdit[i][0] = i;
        }
        for (int i = 1; i < strWord2.length() + 1; i++) {
            minEdit[0][i] = i;
        }

        for (int i = 1; i < strWord1.length() + 1; i++) {
            for (int j = 1; j < strWord2.length() + 1; j++) {
                if (strWord1.charAt(i - 1) != strWord2.charAt(j - 1)) {
                    minEdit[i][j] = 1 + Math.min(Math.min(minEdit[i][j - 1], minEdit[i - 1][j]), minEdit[i - 1][j - 1]);
                } else minEdit[i][j] = minEdit[i - 1][j - 1];

            }

        }
        return minEdit[strWord1.length()][strWord2.length()];
    }

    public static void main(String[] args) {
        System.out.println(levenshteinDistance("catik", "bat"));
    }
}
