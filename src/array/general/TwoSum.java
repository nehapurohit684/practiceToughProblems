package array.general;

import java.util.*;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int results[] = {map.get(target - nums[i]), i};
                return results;
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * this doesnt work if arrray has duplicate elements
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumTwoPtr(int[] nums, int target) {

        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int start = 0;
        int end = sorted.length - 1;

        while (start <= end) {
            if (sorted[start] + sorted[end] > target) {
                end--;
            } else if (sorted[start] + sorted[end] < target) {
                start++;
            } else if (sorted[start] + sorted[end] == target) {
                int results[] = {findIndex(sorted[start], nums), findIndex(sorted[start], nums)};
                return results;
            }
        }
        return null;
    }

    private int findIndex(int i, int[] nums) {

        for (int j = 0; j < nums.length; j++) {
            if (i == nums[j])
                return j;
        }
        return -1;
    }

    public List<String> summaryRanges(int[] nums) {

        List<String> result = new ArrayList<>();

        int start = 0;
        int end = 0;
        if (nums.length == 0) return result;
        if (nums.length == 1) {
            result.add(nums[0] + "");
            return result;
        }
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                continue;
            } else {
                end = i - 1;
                if (start == end) result.add(nums[start] + "");
                else result.add(nums[start] + "->" + nums[end]);
                start = i;
            }
            if (i == nums.length - 1) {
                end = i;
                if (start == end) result.add(nums[start] + "");
                else result.add(nums[start] + "->" + nums[end]);
            }

        }
        if (end != nums.length - 1) {
            end = nums.length - 1;
            if (start == end) result.add(nums[start] + "");
            else result.add(nums[start] + "->" + nums[end]);
        }
        return result;
    }

}
