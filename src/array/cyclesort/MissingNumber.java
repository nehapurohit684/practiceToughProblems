package array.cyclesort;

import java.util.ArrayList;
import java.util.List;

public class MissingNumber {
    /**
     * Leetcode 268
     * Cycle sort
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                int d = nums[i];
                if (d != nums.length) swap(nums, i, d);
                else break;
            }
        }
        //All numbers will be rights place except missing number in range 0 to n
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) return i;
        }
        return nums.length;
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
//448. Find All Numbers Disappeared in an Array

    /**
     * Hint: Here when we try cycle sort we do a sanilty check that numbers
     * we swapping might already be at right position as we can have duplicates numbers
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> list = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                int d = nums[i] - 1;
                if (nums[d] != nums[i]) swap(nums, i, d);
                else break;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) list.add(i + 1);
        }
        return list;
    }

    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                int d = nums[i] - 1;
                if (nums[d] != nums[i]) swap(nums, i, d);
                else break;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) return nums[i];
        }
        return nums[nums.length - 1];
    }

    //645
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                int d = nums[i] - 1;
                if (nums[d] != nums[i]) swap(nums, i, d);
                else break;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                result[0] = nums[i];
                result[1] = i + 1;
            }
        }
        return result;

    }

    /**
     * leetcode 41
     * Hint: Think of it as problem where you are given n places and you need to find smallest missing positive number
     * Positive number could be from 1 to n+1, So we us cycle sort to find which number from 1 to n+1 is missing
     * Missing positive number can never be greater than n+1 in an array of size n .
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                int d = nums[i] - 1;
                if (d >= 0 && d <= nums.length - 1 && nums[d] != nums[i]) swap(i, d, nums);
                else break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }

    //442
    //hint : Wyhe cycle sort to find duplicates , we make sure
    // we dont swap if we see the number is already ta right place else we enter in infinite loop
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                //having a d int = nums[i]-1 made sure you dont get into error or changing values
                if (nums[nums[i] - 1] != nums[i]) swap(nums, i, nums[i] - 1);
                else break;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                list.add(nums[i]);
            }
        }
        return list;
    }

}
