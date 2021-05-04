package array.general;

public class WiggleSort {
    /**
     * 280. Wiggle Sort
     *
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
