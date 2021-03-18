package recursion;

import java.util.*;

public class PermutationsNumberRecursion {
    /**
     * Leetcode 46
     * you call helper and then for recursive case you need to pick one element that will be part of slate,
     * you need to swap this element with start pointer used in recursion,
     * why we need swap because we say next worker to look from start pointer onwards, and we only work on start position of slate.
     * so we append nums[start] in slate . Look problem 46 in leetcode.
     */
    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        helper(nums, 0, new ArrayList<>());
        return results;

    }

    private void helper(int[] nums, int start, ArrayList<Object> slate) {

        if (start >= nums.length) {
            results.add(new ArrayList(slate));
        }

        for (int i = start; i < nums.length; i++) {
            swap(start, i, nums);
            slate.add(nums[start]);
            helper(nums, start + 1, slate);
            slate.remove(slate.size() - 1);
            swap(start, i, nums);

        }


    }

    private void swap(int i, int j, int[] nums) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        helperUnique(nums, 0, new ArrayList<>(), results);
        return results;
    }

    private void helperUnique(int[] nums, int start, ArrayList<Object> slate, List<List<Integer>> results) {
        if (start >= nums.length) {
            results.add(new ArrayList(slate));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            } else {
                set.add(nums[i]);
                swap(start, i, nums);
                slate.add(nums[start]);
                helperUnique(nums, start + 1, slate, results);
                slate.remove(slate.size() - 1);
                swap(start, i, nums);
            }

        }

    }

}
