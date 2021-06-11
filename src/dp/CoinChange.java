package dp;

public class CoinChange {
    /**
     * Leetcode: 322
     * Hint to find min number of coins you need you find min ( memo[amount-c1), memo(amount-c2).. memo(amount-ck))+1
     * c1, c2..ck are values of coins.
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        long[] result = new long[amount+1];

        result[0] = 0;
        for(int i=1;i<amount+1;i++){
            long minVal = (long) Integer.MAX_VALUE;
            for(int j=0;j<coins.length;j++){
                if(i-coins[j]>=0)
                    minVal = Math.min(result[i-coins[j]],minVal);
            }
            result[i] = minVal+1;
        }
        if(result[amount]>=Integer.MAX_VALUE | result[amount]==Integer.MIN_VALUE ) return -1;
        return (int)result[amount];
    }
}
