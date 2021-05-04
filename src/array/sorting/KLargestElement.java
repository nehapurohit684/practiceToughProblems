package array.sorting;

import java.util.PriorityQueue;
import java.util.Random;

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

}
