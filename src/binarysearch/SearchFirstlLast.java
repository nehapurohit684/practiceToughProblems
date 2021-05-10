package binarysearch;

import java.util.Arrays;

public class SearchFirstlLast {
    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * Hint: Two Binary search first to find first occurrence | | | |>t ||<=t | | | ,
     * so divide array in two parts < target and >= target , at the end of search start will point to boundary which is first occurrence of target
     * Next start from first occurrence till end of array, divide araay > target and  == to target ,
     * start will point to first element greater that target , end will point the right most idx of target
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        int[] result = new int[2];
        Arrays.fill(result, -1);
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        if (start == nums.length || nums[start] != target) {
            return result;
        } else result[0] = start;

        end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        if (end == nums.length && nums[end - 1] == target) result[1] = end - 1;
        else result[1] = end;
        return result;
    }

    /**
     * 1150. Check If a Number Is Majority Element in a Sorted Array
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean isMajorityElement(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        if (start == nums.length) return false;
        else if (start + nums.length / 2 < nums.length &&
                nums[start] == nums[start + nums.length / 2]) return true;
        return false;

    }

    /**
     * 74. Search a 2D Matrix
     * Hint : covert to 1 D array , Index of any element (row,col)1 D = row *n+col
     * like index = 3 in matrix(2,3) will be for row*3+col =3, to find row and col
     * row = index/n(3/3) =1, col = index%3= 0 => (1,0)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        int start = 0;
        int end = (matrix.length * matrix[0].length) - 1;


        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid / (matrix[0].length)][mid % (matrix[0].length)] == target)
                return true;
            else if (matrix[mid / (matrix[0].length)][mid % matrix[0].length] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }
}
