package binarysearch;

public class BSRotatedSortArray {
    /**
     * 153. Find Minimum in Rotated Sorted Array
     * you would think that element right after peak is min number but we can not guarantee that
     * because in mountain problem before peak is ascending and after peak is descending
     * here min element is intersection of ascending sorted array
     * To create boundary in this problem you need to compare mid with first or last element so we can tell  how is the array rotated
     * if nums[mid] <nums[0] that means valley is at left side
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int start =0;
        int end = nums.length-1;
        if(start==end) return nums[start];
        while (start<=end){
            int mid  = start + (end-start)/2;
            if(nums[0]>nums[mid]) end= mid-1;
            else start =mid+1;
        }
        return nums[start%nums.length];
    }

    /**
     *
     154. Find Minimum in Rotated Sorted Array II: With duplicate element
     Hint: to properly separate two zones of binary search element at start should be other than element from last as it will hard identify
     whether it belong to right side of mid or left side of mid.
     So point start to index whose value is different than end value of array
     * @param nums
     * @return
     */
    public int findMin1(int[] nums) {

        int start =0;
        int end = nums.length-1;
        if(start==end) return nums[start];
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=nums[nums.length-1]) {
                start =i;
                break;
            }
        }
        if(start==nums.length-1) return nums[0];
        while(start<=end){
            int mid = start +(end-start)/2;
            if(nums[mid]>nums[nums.length-1]) start = mid+1;
            else end =mid-1;
        }
        return nums[start%nums.length];
    }

    /**
     * Search an target in rorated sorted array
     * 1st find where does target belong to , rotated sorted array has two zones
     * one where every element is less than end element (Orange Zone )
     * and other where every element is more than last element (green zone)
     * TO solve this if target is in orange zone and mid is in green zone move start to mid+1
     * if target is in green zone and mid is in orage zone move end
     * if target and mid are in same zone then do regular binary search
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (start == end && nums[start]==target) return start;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid]==target) return mid;
            if (nums[nums.length-1]> nums[mid] && target>nums[nums.length-1]) end = mid - 1;
            else if(nums[nums.length-1]<=nums[mid] && target<=nums[nums.length-1]) start= mid+1;
            else if(nums[mid]<target) start = mid+1;
            else end = mid-1;
        }
        return -1;
    }

    /**
     * 81. Search in Rotated Sorted Array II
     * Same as above but with duplicate elements in array
     * @param nums
     * @param target
     * @return
     */
    public boolean search1(int[] nums, int target) {
        int start =0;
        int end = nums.length-1;
        if(start==end && nums[start]==target) return true;
        if(nums[end]==target) return true;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=nums[nums.length-1]) {
                if(nums[i]==target)return true;
                start =i;
                break;
            }
        }
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid]==target) return true;
            if (nums[nums.length-1]> nums[mid] && target>nums[nums.length-1]) end = mid - 1;
            else if(nums[nums.length-1]<nums[mid] && target<=nums[nums.length-1]) start= mid+1;
            else if(nums[mid]<target) start = mid+1;
            else end = mid-1;
        }
        return false;
    }


}
