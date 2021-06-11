package array.slidingwindow;

public class VariableSlidingWindow {
    /**
     * 209. Minimum Size Subarray Sum
     * Variable length will have two pointers and diff gives length of window
     * instead of keeping k to n cylce we have only one and we create diff size window by moiving left pointer to i.
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){

            //Incorparating nums[i]
            windowSum +=nums[i];
            while(left<=i && windowSum>=target){
                //update window Metrics
                windowSum -=nums[left];
                minLength = Math.min(minLength,i-left+1);
                left++;
            }
        }
        if(minLength==Integer.MAX_VALUE) return 0;
        return minLength;
    }

    /**
     * Given an array of integers nums and an integer k,
     * return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
     *
     *Numer of sub arrays ending at i starting at left = i-left +1 (left to i, left+1 to i, ..i)
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int left =0;
        long product =1l;

        int result =0;
        for(int i=0;i<nums.length;i++){
            product *= nums[i];

            while(left<=i && product>=k){
                product = product/nums[left];
                left++;
            }
            //num of sub arrays ending at it whose prod is less than k
            // number of sub arrays endind at i = i-left+1
            result += i-left+1;
        }
        return result;
    }

    public int minOperations(int[] nums, int x) {

        int k = sum(nums) - x;
        //find max subarray whose sum =k

        int left =0;
        int windowSum = 0;
        int maxLength =  Integer.MIN_VALUE;

        for(int i=0;i<nums.length;i++){
            windowSum += nums[i];

            while(left<=i && windowSum>k){
                windowSum -= nums[left];
                left++;
            }
            if(windowSum==k) {
                maxLength= Math.max(maxLength,i-left+1);
            }

        }
        if(maxLength == Integer.MIN_VALUE ) return -1;
        return nums.length - maxLength;
    }

    int sum(int[] nums){
        int sum = 0;

        for(int i=0;i<nums.length;i++){
            sum += nums[i];

        }
        return sum;
    }


    /**
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {

        //find the window where num of zeroes =k
        int left =0;
        int lenght =0;
        int window0 = 0;
        for(int i=0;i<nums.length;i++){

            if(nums[i]==0) window0++;
            while(left<=i && window0>k){
                if(nums[left]==0) window0--;
                left++;
            }
            lenght = Math.max(lenght, i-left+1);
        }
        return lenght;
    }
}
