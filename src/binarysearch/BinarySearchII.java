package binarysearch;

public class BinarySearchII {

    /**
     * Given an array of integers arr, return true if and only if it is a valid mountain array.
     *
     * Recall that arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     * you will have to llok at all elements so its no actually a binary search
     * @param arr
     * @return
     */
    public boolean validMountainArray(int[] arr) {

        int start = 0;
        int end = arr.length-1;

        while(start<end && arr[start]<arr[start+1]){
            start++;
        }
        while(end>0 && arr[end]<arr[end-1]){
            end--;
        }
        if(start !=0 && end!= arr.length-1 && arr[start]==arr[end]) return true;
        return false;
    }

    /**
     * 852. Peak Index in a Mountain Array
     * Hint: Here to find peak when its gurantee that its mountain array we can use binary search
     * <peak|||||peak|||||<peak.. if each next element is increasing move start to mid+1 if each consecutive number decreasing then move end to mid-1;
     * when we find an element whose value is more than left and right neighbour we return it.
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {

        int start =0;
        int end = arr.length-1;

        while(start<=end){
            int mid = start + (end-start)/2;
            if(mid!=0 && arr[mid-1]<arr[mid] && arr[mid+1]<arr[mid]) return mid;
            else if(mid==0 || arr[mid]<arr[mid+1]) start = mid +1;
            else end = mid-1;
        }
        return -1;
    }

    /**
     * 1095. Find in Mountain Array: find an element not sorted but its a montain array
     * first find peak in mountain array
     * and then search from 0 to peak idx -increasing order array
     * then search from peak to end -decreasing order array
     * @param target
     * @param mountainArr
     * @return
     */

    public int findInMountainArray(int target, MountainArray mountainArr) {

        int start =0;
        int end = mountainArr.length()-1;
        int peak=0;

        while(start<=end){
            int mid = start +(end-start)/2;
            int midVal = mountainArr.get(mid);
            if(mid!=0 && midVal>mountainArr.get(mid-1) &&
                    midVal> mountainArr.get(mid+1)) {
                peak = mid;
                break;
            }else if(mid==0 || midVal>mountainArr.get(mid-1)) start = mid+1;
            else end = mid-1;
        }

        if(target==mountainArr.get(peak)) return peak;
        start =0;
        end = peak-1;
        while(start<=end){
            int m = start +(end-start)/2;
            int midVal =mountainArr.get(m);
            if(midVal==target) return m;
            else if (midVal<target) start = m+1;
            else end = m-1;
        }
        start = peak+1;
        end = mountainArr.length()-1;

        while(start<=end){
            int m = start +(end-start)/2;
            int midVal =mountainArr.get(m);
            if(midVal==target) return m;
            else if (midVal>target) start = m+1;
            else end = m-1;
        }
        return -1;
    }
//fake API class only to remove compilation
    class MountainArray{
        int length() {return 0;}
        int get(int i){ return i;}
    }

    /**
     * 162. Find Peak Element: NOTE: ARRAY IS NOT SORTED STILL BINARY SEARCH CAN WORK
     * Hint handle edge cases separately for array size 1 and peak value first or last
     * if peak is not at start then left most side of array will be ascending
     * if peak is not at last right most side of array will be descending
     * we might also have valley in between in array
     * if we find a index as peak return
     * if we find a index as ascending order then increment start to index (there can be a peak at left also but we are pretrry sure there will be a peak in right)
     * if we find a index in descending order then move end to mid -1
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {

        int start = 0;
        int end  = nums.length-1;

        if(nums.length==1) return 0;
        if(nums[0]>nums[1]) return 0;
        if(nums[nums.length-2]<nums[nums.length-1]) return nums.length-1;

        while(start<=end){

            int mid = start +(end-start)/2;
            if(mid!=0 && nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1]) return mid;
                //if mid is valley
                //  else if(nums[mid]<nums[mid-1] && nums[mid]<nums[mid+1]) end = mid-1;
                // if mid is in accesnding zone
            else if(mid==0 || nums[mid]<nums[mid+1]) start = mid+1;
                //if mid in descending zone
            else end = mid-1;

        }
        return -1;

    }
}
