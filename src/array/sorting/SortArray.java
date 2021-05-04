package array.sorting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SortArray {

    public int[] sortArray(int[] nums) {

        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {

        Random rand = new Random();

        int pivotInd = rand.nextInt((end - start) + 1) + start;
        int smallInd = start;
        int pivotVal = nums[pivotInd];
        swap(smallInd, pivotInd, nums);
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] < pivotVal) {
                smallInd++;
                swap(smallInd, i, nums);
            }
        }
        swap(smallInd, start, nums);
        quickSort(nums, start, smallInd - 1);
        quickSort(nums, smallInd + 1, end);
    }

    private void swap(int smallInd, int pivotInd, int[] nums) {
        int temp = nums[smallInd];
        nums[smallInd] = nums[pivotInd];
        nums[pivotInd] = temp;
    }

    /**
     * leetcode 697
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {

        Map<Integer, Integer> count = new HashMap<>(), left = new HashMap<>(), right = new HashMap<>();
//left and right to keep track of index and count to track max degree calculation
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0).intValue() + 1);

            if (!left.containsKey(nums[i])) left.put(nums[i], i);
            right.put(nums[i], i);
        }

        int degree = Collections.max(count.values());
        int minSizeofArray = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (count.get(nums[i]) == degree) {
                minSizeofArray = Math.min(minSizeofArray, right.get(nums[i]) - left.get(nums[i]) + 1);
            }
        }
        return minSizeofArray;
    }

}
