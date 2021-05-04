package array.prefixsum;

public class PrefixNumArray {
    /**
     * leetcode 303
     * hint: Enhance the input by keeping prefix sum of each number in advance
     */
    int[] prefixSums;

    public PrefixNumArray(int[] nums) {
        prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) return prefixSums[right];
        return prefixSums[right] - prefixSums[left - 1];

    }
}
