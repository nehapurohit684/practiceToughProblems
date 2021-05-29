package array.general;

public class WiggleSort {
    /**
     * 280. Wiggle Sort
     *Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
     * even should be less than next odd and ood idx number should be  greater than previous even idx number
     * At every even number including 0 compare if nums[i]<  nums[i-1] then swap
     * At every odd number
     * @param nums
     */
    public void wiggleSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 != 0) {
                if (nums[i - 1] > nums[i]) swap(nums, i, i - 1);
            } else {
                if (nums[i - 1] < nums[i]) swap(nums, i, i - 1);
            }
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
