package dp;

public class LongestCommonSequence {

    public static int longestCommonSubsequence(String text1, String text2) {

        int[][] memo = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i < text1.length(); i++) {
            memo[i][0] = 0;
        }
        for (int i = 0; i < text2.length(); i++) {
            memo[0][i] = 0;
        }

        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                else {
                    memo[i][j] = Math.max(Math.max(memo[i - 1][j], memo[i][j - 1]), memo[i - 1][j - 1]);
                }
            }

        }

        return memo[text1.length()][text2.length()];

    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("nest", "test"));
    }

    static long countWaysToClimb(int[] steps, int n) {

        long[] memo = new long[n + 1];

        for (int j = 0; j < steps[0]; j++) {
            memo[j] = 0;
        }

        for (int i = 0; i < steps.length; i++) {
            memo[steps[i]] = 1;
        }

        for (int k = (steps[0] + 1); k < n + 1; k++) {
            for (int i = 0; i < steps.length; i++) {
                memo[k] = memo[k] + memo[n - steps[i]];
            }
        }

        return memo[n];

    }
}
