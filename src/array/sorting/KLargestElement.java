package array.sorting;

import java.util.*;

public class KLargestElement {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : nums) {
            if (pq.size() > k) {
                pq.add(nums[i]);
                pq.poll();
            }
            pq.add(nums[i]);
        }
        return pq.poll();
    }

    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     *
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * You must solve it in O(n) time complexity.
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestQ(int[] nums, int k) {
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        Random rand = new Random();
        int pivot = rand.nextInt((end - start) + 1) + start;
        int pivotValue = nums[pivot];
        int startInd = start;
        swap(startInd, pivot, nums);

        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] < pivotValue) {
                startInd++;
                swap(i, startInd, nums);
            }
        }
        swap(startInd, start, nums);

        if (k == startInd) return nums[k];
        else if (k < startInd) return partition(nums, start, startInd - 1, k);
        else return partition(nums, startInd + 1, end, k);
    }

    private void swap(int smallInd, int pivotInd, int[] nums) {
        int temp = nums[smallInd];
        nums[smallInd] = nums[pivotInd];
        nums[pivotInd] = temp;
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k;

    public KLargestElement(int k, int[] nums) {
        this.k = k;
        for (int i : nums) {
            if (pq.size() >= k) {
                pq.add(i);
                pq.poll();
            } else {
                pq.add(i);
            }
        }
    }

    public int add(int val) {
        if (pq.size() >= k && pq.peek() <= val) {
            pq.add(val);
            pq.poll();
        } else if (pq.size() < k) {
            pq.add(val);
        }
        return pq.peek();
    }
    /**
     * A distinct string is a string that is present only once in an array.
     *
     * Given an array of strings arr, and an integer k, return the kth distinct string present in arr.
     * If there are fewer than k distinct strings, return an empty string "".
     *
     * Note that the strings are considered in the order in which they appear in the array.
     * @param arr
     * @param k
     * @return
     */
    public String kthDistinct(String[] arr, int k) {

        Map<String,Integer> map = new LinkedHashMap();

        for(String s: arr){
            map.put(s,map.getOrDefault(s,0)+1);

        }
        List<String> stringsList = new LinkedList<>();

        for(Map.Entry<String,Integer> entry  : map.entrySet()){
            if(entry.getValue()==1) stringsList.add(entry.getKey());
        }

        return stringsList.size()<k ? "":stringsList.get(k);

    }
}
