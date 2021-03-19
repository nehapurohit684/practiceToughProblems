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

    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * backtracking: backtracking fucntion comes before base case
     * In this case the base case will only reach when slate's length <k we dont want that slate to be added to results so instead of adding as we did in sub set problem we only return
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();

        helperCombine(n, 1, new ArrayList<>(), results, k);

        return results;
    }

    private void helperCombine(int n, int start, List<Integer> slate, List<List<Integer>> results, int k) {

        if (slate.size() == k) {
            results.add(new ArrayList<>(slate));
            return;
        }
        if (start > n) {
            return;
        }
        //exclude
        helperCombine(n, start + 1, slate, results, k);
        //include
        slate.add(start);
        helperCombine(n, start + 1, slate, results, k);
        slate.remove(slate.size() - 1);
    }

    /**
     * Leecode 40: Here again sort array first to avoid duplicate sub arrays
     * Then instead of taking decision to inclde or exclude for each element , we take decision to multi inclusion of one element and its exclusion
     * We backtraack whenever sum == target
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        helperSum2(candidates, 0, new ArrayList<>(), 0, results, target);
        return results;
    }

    private void helperSum2(int[] candidates, int start, List<Integer> slate, int slateSum, List<List<Integer>> results, int target) {
        //Backtarking
        if (slateSum == target) {
            results.add(new ArrayList<>(slate));
            return;
        } else if (slateSum > target) return;

        if (start >= candidates.length) {
            return;
        }

        int count = 0;
        for (int i = start; i < candidates.length; i++) {
            if (candidates[start] == candidates[i])
                count++;
        }
        //exclude scenario
        helperSum2(candidates, start + count, slate, slateSum, results, target);
        //include
        for (int i = 1; i <= count; i++) {
            slate.add(candidates[start]);
            helperSum2(candidates, start + count, slate, slateSum + (i * candidates[start]), results, target);
        }
        for (int i = 1; i <= count; i++) {
            slate.remove(slate.size() - 1);
        }
    }
}
