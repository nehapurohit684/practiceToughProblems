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

    /**
     * 325. Maximum Size Subarray Sum Equals k
     * Hint Try to finf what needs to happen for prefix sum from 0 to i
     * in this case to have max length for sum =k from j to i , there should be min length for sum = prefix (0,i)-k
     * length for that sum total length (i+1) - length of prefix having sum (p-k)
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        int prefixSum =0;
        int result = 0;
        for(int i =0;i<nums.length;i++){
            prefixSum += nums[i];
            if(map.containsKey(prefixSum-k)){
                result = Math.max((i+1-map.get(prefixSum-k)),result);
            }
            map.putIfAbsent(prefixSum,i+1);
        }
        return result;
    }

    /**
     * 1524. Number of Sub-arrays With Odd Sum
     * Given an array of integers arr. Return the number of sub-arrays with odd sum.
     *As the answer may grow large, the answer must be computed modulo 10^9 + 7.
     *HInt: in Map you have to keep only two keys 0 and 1 for remainder,
     * for any index i if prefix sum is odd then a previous prefix sum of even would make a array of tatal sum =odd
     * similarly if prefix sum sum is even then a previous prefix sum of odd could make s sub array of tatal sum =even
     * @param arr
     * @return
     */
    public int numOfSubarrays(int[] arr) {

        Map<Long,Integer> map = new HashMap<>();
        long prefixSum = 0;
        long result = 0;
        map.put(0L,1);
        map.put(1L,0);
        for (int i=0;i<arr.length;i++){
            prefixSum = (prefixSum +arr[i]);

            if(prefixSum%2==0) {
                result += map.get(1L) ;
                result = result% 1000000007;
            }
            if(prefixSum%2!=0) {
                result += map.get(0L) ;
                result=result% 1000000007;
            }
            map.put(prefixSum%2,map.get(prefixSum%2)+1);

        }
        return (int) result;
    }

    /**
     * leetcode 525 :
     * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
     * Here we want to keep track of number of ones and zeroes and excessvalue or difference between # of 1s and # of 0s
     * When we see a difference we check if that difference belong to prefx array if yes that means we found an array of equal 1s and 0s
     * we keep checking it and updating max length
     * IN Map we keep excees value and min length of prefix array that has that excees value.
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {

        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        int exceesValue = 0;
        int result =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                exceesValue +=1;
            }
            else {
                exceesValue -=1;
            }
            if(map.containsKey(exceesValue)){
                result = Math.max(result, (i+1)-map.get(exceesValue));
            }
            map.putIfAbsent(exceesValue,i+1);
        }
        return result;
    }

    /**
     * 523. Continuous Subarray Sum
     * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
     *
     * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
     * Combination of divisible by k sum and putting length for each remainder instead of number of arrays with that remainder
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int prefixSum = 0;
        map.put(0,0);
        for(int i=0;i<nums.length;i++){
            prefixSum += nums[i];
            int searchKey = prefixSum %k;
            if(searchKey<0) searchKey+=k;
            if(map.containsKey(searchKey)){
                if(i+1-map.get(searchKey)>=2) return true;
            }
            map.putIfAbsent(searchKey,i+1);
        }

        return false;
    }
}
