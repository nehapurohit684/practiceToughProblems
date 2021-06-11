package dp;

public class RobHouse {
    /**
     * Given an integer array nums representing the amount of money of each house, robber can go on alternate houses
     * return the maximum amount of money you can rob tonight without alerting the police.
     * Hint: At last house it can ro or not rob , it will choose an option which will maximize is value
     * f(n) = Max(f(n-1),f(n-2)+n)
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];

        int[] memo = new int[nums.length];
        memo[0]=nums[0];
        memo[1] = Math.max(nums[1],nums[0]);

        for(int i=2;i<nums.length;i++){
            memo[i] = Math.max(memo[i-1],memo[i-2]+nums[i]);
        }

        return memo[nums.length-1];
    }

    public int robCycle(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.max(nums[0],nums[1]);
        if(nums.length==3) return Math.max(Math.max(nums[0],nums[1]),nums[2]);
        if(nums.length==4) return Math.max(nums[0] + nums[2],nums[1]+nums[3]);

        //if you are robbing first house you cant rob 1st house and last house
        int[] memo = new int[nums.length];
        memo[2]=nums[2];
        memo[3] = Math.max(nums[3],nums[2]);

        for(int i=4;i<nums.length-1;i++){
            memo[i] = Math.max(memo[i-1],memo[i-2]+nums[i]);
        }
        int case1 = memo[nums.length-2] + nums[0];
        //if you are not robbing first house you can rob last house also - st line problem 1 to n-1
        int[] memo1 = new int[nums.length];
        memo1[1]=nums[1];
        memo1[2] = Math.max(nums[1],nums[2]);

        for(int i=3;i<nums.length;i++){
            memo1[i] = Math.max(memo1[i-1],memo1[i-2]+nums[i]);
        }
        int case2 = memo1[nums.length-1];

        return Math.max(case1,case2);


    }
}
