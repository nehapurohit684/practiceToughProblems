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

    ;
}
