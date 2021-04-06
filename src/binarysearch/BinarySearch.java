package binarysearch;

public class BinarySearch {

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
     * When binary search ends start = end+1 and they both are are boundary of condition we are checking
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
}
