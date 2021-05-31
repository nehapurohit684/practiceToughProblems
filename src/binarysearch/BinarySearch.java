package binarysearch;

import java.util.List;

public class BinarySearch {

    /**
     * Leetcode 704
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + (end - start)) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    /**
     * 374 Guess the Number
     *
     * @param n
     * @return
     */
    public int guessNumber(int n) {

        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // if(guess(mid)==0)  return mid;
            // else if(guess(mid)==-1) end =mid-1;
            //else start =mid+1;
        }
        return -1;
    }

    /**
     * 278
     * When binary search ends start = end+1 and
     * they both are are boundary of condition we are checking
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {

        int start = 1;
        int end = n;
        while ((start <= end)) {
            int mid = start + (end - start) / 2;
            //  if(isBadVersion(mid)) end = mid-1;
            //else start =mid+1;
        }
        return start;
    }

//35. Search Insert Position

    /**
     * Hint : When Binary Search ends:
     * Start = end+1 ,End points to largest number <x, Start points to smallest number >x</x,>
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return start;
    }

    /**
     * 744. Find Smallest Letter Greater Than Target
     * Hint : when letter is less than or equal to your mid you increment start index.
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {

        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;
            if (letters[mid] <= target) start = mid + 1;
            else end = mid - 1;
        }
        return letters[start % letters.length];
    }

    /**
     * 1428. Leftmost Column with at Least a One
     * Binary search for each row
     *
     * @param binaryMatrix
     * @return
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {

        int row = binaryMatrix.dimensions().get(0);
        int col = binaryMatrix.dimensions().get(1);
        int leftMostCol = col;
        ;
        for (int i = 0; i < row; i++) {
            int start = 0;
            int end = col - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (binaryMatrix.get(i, mid) == 0) {
                    start = mid + 1;
                } else end = mid - 1;
            }
            start = start >= col ? col - 1 : start;
            if (binaryMatrix.get(i, start) == 1) {
                leftMostCol = Math.min(leftMostCol, start);
            }
        }

        return leftMostCol == col ? -1 : leftMostCol;
    }

    interface BinaryMatrix {
        public int get(int row, int col);

        public List<Integer> dimensions();
    }
    //702. Search in a Sorted Array of Unknown Size
    // if you access the array out of bounds, ArrayReader.get will return 2147483647.
    //Hint : First find the end index for that keep going until you find number greater than target then do binary search
    public int search(//ArrayReader reader,
                       int target) {

        int start =0;
       // if(reader.get(start)==target) return start;
        int end=1;
//        while(reader.get(end)<target){
//            end=end *2;
//        }
        while(start<=end){
            int mid = start+(end-start)/2;
            int midVal=0;//=reader.get(mid);

            if(midVal<target) start = mid+1;
            else if(midVal>target) end = mid-1;
            else return mid;

        }
        return -1;
    }

    /**
     * leet code 1064
     * Given an array of distinct integers arr, where arr is sorted in ascending order,
     * return the smallest index i that satisfies arr[i] == i. If there is no such index, return -1.
     * Hint: if a[i]<i that means a possible index where a[i]=i will be from i+1 till end of array
     * @param arr
     * @return
     */
    public int fixedPoint(int[] arr) {

        int start =0;
        int end = arr.length-1;

        while(start<=end){
            int mid = start +(end-start)/2;
            if(arr[mid]>=mid)
                end = mid-1;
            else if(arr[mid]<mid) start =mid+1;
        }
        if(start<arr.length-1 && arr[start]==start) return start;
        return -1;
    }

    /**
     * You are given a sorted array consisting of only integers where every element appears exactly twice,
     * except for one element which appears exactly once. Find this single element that appears only once.
     * Hint: ANy number not eq prev and next is our ans , Also before a non repeating number nums[even]=nums[even+1]
     * but after an anamoly nums[odd]=nums[oddd+1]
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {

        int start = 0;
        int end =nums.length-1;
        if(nums.length==1) return nums[0];
        if(nums[0]!=nums[1]) return nums[0];
        if(nums[end]!=nums[end-1]) return nums[end];


        while(start<=end){
            int mid = start +(end-start)/2;
            if(nums[mid]!=nums[mid-1] && nums[mid]!=nums[mid+1]) return nums[mid];
            else if(mid%2==0 && nums[mid]==nums[mid+1] ||
                    mid%2!=0  && nums[mid]==nums[mid-1]) start = mid+1;
            else end = mid-1;
        }
        return -1;
    }

    /**
     * 1228. Missing Number In Arithmetic Progression
     * Hint : after missing number all numbers are not at right ap index
     * for increasing AP if any number is greater than or equal to expected AP number
     * that means index of missing AP number is in left side
     * @param arr
     * @return
     */
    public int missingNumber(int[] arr) {

        int ap = (arr[arr.length-1]-arr[0]) / arr.length;

        int start = 0;
        int end = arr.length-1;

        if(arr[start]==arr[end]) return arr[start];

        while(start<=end){

            int mid = start +(end-start)/2;
            int expMid = arr[0] + (mid * ap) ;
            if(ap>0){
                if(expMid>= arr[mid]) start = start+1;
                else end = mid-1;
            }
            if(ap<0){
                if(expMid<= arr[mid]) start = start+1;
                else end = mid-1;
            }
        }
        if(start<=arr.length-1) return arr[0] + (start * ap);

        return -1;
    }

    /**
     * 1060. Missing Element in Sorted Array
     * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
     * return the kth missing number starting from the leftmost number of the array.
     * Hint: Number of missing numbers at any index = num[index] - (num[0]+index)
     * when num of missing number is <k we can move start index to mid+1 , else move end to mid-1
     * @param nums
     * @param k
     * @return
     */
    public int missingElement(int[] nums, int k) {

        int start = 0;
        int end  = nums.length-1;

        while(start<=end){
            int mid  = start +(end-start)/2;
            if(nums[mid]-(nums[0]+mid)<k) start =mid+1;
            else end = mid-1;
        }
        //end will point to last number which has totalmissing less than k so we have to find number by adding k-totalmissing to nums[end]
        int totalMissing = nums[end] -(nums[0]+end);
        return nums[end]+ (k-totalMissing);
    }
}
