package array.prefixsum;

public class RunninfSumArray {
    /**
     * Leetcode 1480
     * 1480. Running Sum of 1d Array:
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i])
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] runningSum = new int[nums.length];
        runningSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            runningSum[i] = runningSum[i - 1] + nums[i];
        }
        return runningSum;
    }
}
