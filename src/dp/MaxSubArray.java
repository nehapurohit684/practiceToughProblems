package dp;

public class MaxSubArray {
    /**
     * Given an integer array nums, find the contiguous subarray
     * (containing at least one number)
     * which has the largest sum and return its sum.
     * Hint: At every position i, you find local max sum = max(prevSum till i-1 + nums[i] or nums[i]
     * Then you find globalMax by Math.max(globalMax,localMax)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        int prevSum = 0;
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int localSum = Math.max(prevSum + nums[i], nums[i]);
            globalMax = Math.max(localSum, globalMax);
            prevSum = localSum;
        }
        return globalMax;
    }

    /**
     * Leetcode 121. Best Time to Buy and Sell Stock
     * Hint: For ith position find the min price from 0 to i-1
     * Find Max Profit = max(prices[i]-min,maxProfit)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        int localMin = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            localMin = Math.min(localMin, prices[i]);
            maxProfit = Math.max(prices[i] - localMin, maxProfit);
        }
        return maxProfit;

    }
}
