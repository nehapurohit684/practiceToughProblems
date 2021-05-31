package binarysearch;
/**
 * General Note : Binary search can be used to optimization problem where we know the possible values of the field we are optimizing
 * We should also have some constraint to divide orginal problem in two arrays varying from min to mid and mid to max.
 * For e.g. to find the max path sum in gird we could first find min(start) and max(end) values of sum
 * but when we find mid how can we say that out sum would belong to any zone -- Bisection Optimization wont work here.
 */
public class BinarySearchOptimization {
    /**
     * leetcode: Return the minimum integer k such that she can eat all the bananas within h hours.
     * Hint: Ask is to find mi speed so that she finishes bananas in excact h hours.
     * So we can do binary search on speed -speed can go from 0 to max element in piles of bananas
     * we see with each speed how much time does it take to finish all bananas,
     * if the time is less than h means , koko is eating too fast to finish all bananas
     * if the time is more than h that means koko wont be bale to finish in h hours
     * when binary search fails with tow zones, time < h and time > h the start indicates max speed with which koko will be able to finish bananas in h hours.
     * @param piles
     * @param h
     * @return
     */

    public int minEatingSpeed(int[] piles, int h) {

        int start =0;
        int end  = max(piles);

        while(start<=end){
            int mid = start +(end-start)/2;
            double midVal =0;

            for (int i:piles) {
                midVal += Math.ceil((double) i/mid);
            }
            if(midVal>h)start = mid+1;
            else end =mid-1;
        }
        return start;
    }

    private int max(int[] piles) {
        int max = piles[0];
        for (int i : piles) {
            if(max<i) max =i;
        }
        return max;
    }

    /**
     * 1011. Capacity To Ship Packages Within D Days
     * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
     * What are we optimizing , and what are range of the values of that field do BS on that
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {

        int start = max(weights);
        int end = sum(weights);

        while (start<=end){
            int mid  = start + (end-start)/2;
            int midval =1;
            int maxWeight =0;
            for (int w: weights) {
                maxWeight =maxWeight+w;
                if(maxWeight>mid){
                    midval++;
                    maxWeight =w;
                }
            }
            if(midval>days)start =mid+1;
            else end =mid-1;
        }
        return start;
    }

    private int sum(int[] piles) {
        int sum=0;
        for (int i : piles) {
            sum+=i;
        }        return sum;
    }

    /**
     * 774. Minimize Max Distance to Gas Station: Bisection search on continous array
     * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.
     *
     * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.
     *
     * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
     *
     * Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.
     * @param stations
     * @param k
     * @return
     */
    public double minmaxGasDist(int[] stations, int k) {
        //We are minimizing D between adjacent gas staion by adding more gas stations
        double startD =0;
        //Max distance between any any two stations
        double endD =0;

        for(int i =0; i<stations.length-1;i++){
            endD = Math.max(stations[i+1]-stations[1],endD);
        }

        while(startD<=endD-0.000001){

            double midD= startD +(endD-startD)/2;

            //what is number of gas stations we need to keep miDdistance
            int midDValue = findNumGasStations(midD,stations);
            if(midDValue>k) startD = midD;
            else endD =midD;
        }
        return startD;
    }

    /**
     to find the number of gas stations between any two station to have min dist = golaD
     == totaldistance /goalD , and floor of ii.. because when you add k stations you add k+1 zone between two stations.
     **/
    int findNumGasStations(double goalD,int[] stations){

        int numOfGasStations =0;
        for(int i =0; i<stations.length-1;i++){
            if(goalD >= (stations[i+1]-stations[i])) continue;
            else {
                numOfGasStations += Math.floor ((stations[i+1]-stations[i])/goalD);
            }

        }
        return numOfGasStations;
    }

    /**
     * 410. Split Array Largest Sum
     * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
     *
     * Write an algorithm to minimize the largest sum among these m subarrays.
     * This one is similar to conveyor belt problem, think of it as max capacity that can go in array
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {

        int start  = max(nums);
        int end  = sum(nums);

        while(start<=end){

            int mid  = start +(end-start)/2;
            // Can we find number of subrarrays such as max sum of each array is less than or = mid , if
            // mid could be the largest sum with keeping number of arrays as m that our ans
            int numOfSubArrays  = findNumArrays(mid,nums);
            // to keep max sum as mid we need more arrays then m that means we have to increase the max sum so that we can keep m sub arrays.
            if(numOfSubArrays>m) start =mid+1;
            else end = mid-1;
       }
        return start;
    }

    private int findNumArrays(int maxSum, int[] nums) {
        int numOArrays =1;
        int sumSoFar = 0;
        for (int i = 0; i < nums.length; i++) {
            sumSoFar += nums[i];
            if(sumSoFar>maxSum){
             numOArrays++;
             sumSoFar = nums[i];
            }
        }
        return numOArrays;
    }

    /**
     * 1231. Divide Chocolate
     * Question: find the max sweetness chocalte that i can take and still give my friends more or equal sweetness as me.
     * What are we optimizing : Sum of sub array that i can keep it to myself (total sweetness of my piece)
     * such that this sum is less than or equal of all k pieces i gave to my friends
     * @param sweetness
     * @param K
     * @return
     */
    public int maximizeSweetness(int[] sweetness, int K) {

        int start = min(sweetness);
        int end = sum(sweetness);

        while (start<=end){

            int midSum = start +(end-start)/2;
            // we need to find if I take midSum how many sub arrays I can give it to my friends
            int numOfPieces = findNumArraysChoclate(sweetness,midSum);
            if(numOfPieces>K+1) start =midSum +1;
            else end = midSum-1;
        }
        return end;
    }

    /**
     * find the number of peices that can be created with max sum = midSum
     * so we start with num = 0 and then add a number to it only when we are eq or greater than midSum
     * @param sweetness
     * @param midSum
     * @return
     */
    private int findNumArraysChoclate(int[] sweetness, int midSum) {

        int numOfPeices = 0;
        int sumSoFar = 0;

        for (int i = 0; i < sweetness.length; i++) {
            sumSoFar += sweetness[i];
            if(sumSoFar>=midSum){
                numOfPeices++;
                sumSoFar = 0;
            }
        }
        return numOfPeices;
    }

    private int min(int[] sweetness) {
        int min = Integer.MAX_VALUE;
        for (int i: sweetness) {
            min  = Math.min(i,min);
        }
        return min;
    }

}
