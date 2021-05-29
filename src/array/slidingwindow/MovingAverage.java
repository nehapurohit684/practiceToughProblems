package array.slidingwindow;

import java.util.*;

/**
 * leetcode 346
 * Hint: Maintain Queue for maintaining size of sliding window in data stream,
 * In array we dont have to maintain size using queue
 */
public class MovingAverage {

    Queue<Integer> q;
    int totalSoFar;
    int mySize;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        q = new LinkedList<>();
        totalSoFar = 0;
        this.mySize = size;
    }

    public double next(int val) {
        totalSoFar += val;
        q.add(val);
        if (q.size() > mySize) totalSoFar = totalSoFar - q.poll();
        return (double) totalSoFar / q.size();
    }

    /**
     * 643. Maximum Average Subarray I
     *
     * @param nums
     * @param k
     * @return
     */

    public double findMaxAverage(int[] nums, int k) {
        //Initialize the window
        int windowSum = sum(nums, 0, k);
        int maxSum = windowSum;
        double curAvg = (double) windowSum / k;
        //Update the window & metrics add the rightmost element subtract left most element
        //update the global ans based on local window ans
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        //return global ans
        return (double) maxSum / k;
    }

    private int sum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum = sum + nums[i];
        }
        return sum;
    }

    /**
     * 1343s
     *
     * @param nums
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays(int[] nums, int k, int threshold) {

        //Initialize the window
        int windowSum = sum(nums, 0, k);
        double average = (double) windowSum / k;
        int count = 0;
        if (average >= threshold) count++;
        //Update the window & metrics add the rightmost element subtract left most element
        //update the global ans based on local window ans
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            average = (double) windowSum / k;
            if (average >= threshold) count++;
        }
        //return global ans
        return count;

    }

    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {

        //Initialize the window
        int windowSum = sum(calories, 0, k);
        int point = 0;
        if (windowSum > upper) point++;
        else if (windowSum < lower) point--;
        //Update the window & metrics add the rightmost element subtract left most element
        //update the global ans based on local window ans
        for (int i = k; i < calories.length; i++) {
            windowSum += calories[i] - calories[i - k];
            if (windowSum > upper) point++;
            else if (windowSum < lower) point--;
            else continue;
        }
        //return global ans
        return point;
    }

    /**
     * 1052. Grumpy Bookstore Owner
     * Hint: For each window size X, find how many max customers are gumpy ,
     * i.e maximum sum of fixed window X where grumpy[] =1.
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {

        int numSatisfied = 0;
        int numAngry = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                numSatisfied += customers[i];
            }
        }
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                numAngry += customers[i];
            }
        }
        int globalMaxAngry = numAngry;
        for (int i = X; i < customers.length; i++) {
            if (grumpy[i] == 1) {
                numAngry += customers[i];
            }
            if (grumpy[i - X] == 1) {
                numAngry -= customers[i - X];
            }
            globalMaxAngry = Math.max(numAngry, globalMaxAngry);
        }
        return globalMaxAngry + numSatisfied;
    }

}
