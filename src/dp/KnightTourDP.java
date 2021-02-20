package dp;

/**
 * Given a phone keypad as shown below:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * – 0 –
 * How many different phone numbers of given length can be formed starting from the given digit? The constraint is that the movement from one digit to the next is similar to the movement of the Knight in chess.
 * Hint: Number of different ways to phoneNumber for a given length n =starting with s = Sum of all neighbours Number of ways to form phoneNumber of length n-1 from S
 */

public class KnightTourDP {

    static long numPhoneNumbers(int startdigit, int phonenumberlength) {
        /*
         * Write your code here.
         */

        long[][] memo = new long[10][phonenumberlength + 1];
        int[][] neighbours = {{6, 4}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

        for (int i = 0; i < 10; i++) {
            memo[i][0] = 0;
            memo[i][1] = 1L;
        }

        for (int j = 2; j < phonenumberlength + 1; j++) {
            for (int i = 0; i < 10; i++) {
                for (int neighbour : neighbours[i]) {
                    memo[i][j] += (long) memo[neighbour][j - 1];
                }
            }
        }

        return memo[startdigit][phonenumberlength];
    }

    public static void main(String[] args) {
        System.out.println(numPhoneNumbers(1, 3));
    }
}
