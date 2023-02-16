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

    private void quickSortLomuto(int[] nums, int start, int end) {
        //leaf level
        if(start>=end) return;
        //internal node worker
        Random rand = new Random();
        int randIdx = rand.nextInt(end-start+1) +start;

        int pivotval = nums[randIdx];
        int smallerInd = start;
        swap(start,randIdx,nums);
//lomuto's partition
        for (int bigger = start+1; bigger <= end; bigger++) {
            if(nums[bigger]<=pivotval) {
                smallerInd++;
                swap(smallerInd,bigger,nums);
            }
        }
        swap(start,smallerInd,nums);
        quickSort(nums,start,smallerInd-1);
        quickSort(nums,smallerInd+1,end);
    }

    private void quickSortHoare(int[] nums, int start, int end) {
        //leaf level
        if(start>=end) return;
        //internal node worker
        Random rand = new Random();
        int randIdx = rand.nextInt(end-start+1) +start;

        int pivotval = nums[randIdx];

        swap(start,randIdx,nums);
        int smallerInd = start+1;
        int biggerInd = end;
//Hoare's partition
       while(smallerInd<=biggerInd){
           if(nums[smallerInd]<pivotval)
               smallerInd++;
           else if(nums[biggerInd]>pivotval)
               biggerInd--;
           else {
               swap(smallerInd, biggerInd, nums);
               smallerInd++;
               biggerInd--;
           }
       }
        swap(start,biggerInd,nums);
        quickSort(nums,start,biggerInd-1);
        quickSort(nums,biggerInd+1,end);
    }

    private void quickSort(int[] nums, int start, int end) {

        Random rand = new Random();

        int pivotInd = rand.nextInt((end - start) + 1) + start;
        int smallInd = start;
        int pivotVal = nums[pivotInd];
        swap(smallInd, pivotInd, nums);
        for (int i = start + 1; i <=end; i++) {
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
