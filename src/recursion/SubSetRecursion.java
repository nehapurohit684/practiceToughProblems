package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetRecursion {

    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> slate = new ArrayList<>();
        helper(nums, 0, slate);
        return results;
    }

    private void helper(int[] nums, int start, List<Integer> slate) {
        if (start == nums.length) {
            results.add(new ArrayList(slate));
            return;
        }

        helper(nums, start + 1, slate);
        slate.add(nums[start]);
        helper(nums, start + 1, slate);
        slate.remove(slate.size() - 1);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        helperWithDup(nums, 0, new ArrayList<>(), results);
        return results;
    }

    /**
     * Leetcode 90
     * Instead of making decision of include or exclude of a number we make decision on exclude and include 1 or more copies of the number,
     * nodes to who I am delegating will not be taking decision about the number I have already decided on,
     * so counter will not move by 1 in each recursive call but it will move by number of times any number appear in set.
     * Remember to sort given array at start.
     *
     * @param nums
     * @param start
     * @param slate
     * @param results
     */
    private void helperWithDup(int[] nums, int start, List<Integer> slate, List<List<Integer>> results) {
        if (start >= nums.length) {
            results.add(new ArrayList<>(slate));
            return;
        }
        int count = 0;
        for (int i = start; i < nums.length; i++) {
            if (nums[start] == nums[i])
                count++;
        }
        //exclude scenario
        helperWithDup(nums, start + count, slate, results);
        //include
        for (int i = 1; i <= count; i++) {
            slate.add(nums[start]);
            helperWithDup(nums, start + count, slate, results);
        }
        for (int i = 1; i <= count; i++) {
            slate.remove(slate.size() - 1);
        }

    }
}
