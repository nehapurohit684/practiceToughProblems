package array.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {
    /**
     * 560: return the total number of continuous subarrays whose sum equals to k.
     * hint: create a map for each pre fix sum and if prefix sum = prefixsum till i - k then there exist sub array ending on i that have some k.
     * For every sufflix array with sum =k , there exist a prefix array with sum = p-k , so we count number of arrays whose sum is = prefix-k
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> sumMap = new HashMap<>();
        int prefixSum = 0;
        int count = 0;
        sumMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {

            prefixSum = prefixSum + nums[i];
            if (sumMap.containsKey(prefixSum - k))
                count += sumMap.get(prefixSum - k);
            sumMap.put(prefixSum, sumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    /**
     * leetcode 974
     * hint: A(j...i) = A(0..i)-A(0,j-1)
     * if we want A(j,i) is divisible by k that means A(0..i)-A(0,j-1)% k should be 0
     * that means remainder when A(0,i) is divided by k will give same remainder as A(0,j-)will give.
     * if any time remainder is -ve then we have to add K to make +ve entries in hashmap
     *
     * @return
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int prefixSum = 0;
        int count = 0;
        sumMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum = prefixSum + nums[i];
            int searchKey = prefixSum % k;
            if (searchKey < 0)
                searchKey = searchKey + k;
            if (sumMap.containsKey(searchKey)) {
                count += sumMap.get(searchKey);
                sumMap.put(searchKey, sumMap.get(searchKey) + 1);
            } else sumMap.put(searchKey, 1);
        }
        return count;
    }
}
