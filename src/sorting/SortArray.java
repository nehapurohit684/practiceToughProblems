package sorting;

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

}
