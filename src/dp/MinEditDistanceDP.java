package dp;

public class MinEditDistanceDP {

    public static int minDistance(String word1, String word2) {

        int[][] memo = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < word1.length() + 1; i++) {
            memo[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            memo[0][i] = i;
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    memo[i][j] = 1 + Math.min(Math.min(memo[i][j - 1], memo[i - 1][j]), memo[i - 1][j - 1]);
                } else
                    memo[i][j] = memo[i - 1][j - 1];
            }
        }
        return memo[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("Neha", "Amit"));
    }
}
