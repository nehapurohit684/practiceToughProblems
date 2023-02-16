package array.sorting;

import java.util.*;

class KthLargest {
    PriorityQueue pq = new PriorityQueue();
    int k;



    public KthLargest(int k, int[] nums) {
        this.k =k;
        for( int i=0;i<nums.length;i++){
            if(pq.size()>=k){
                pq.add(nums[i]);
                pq.poll();
            }else {
                pq.add(nums[i]);
            }
        }


    }



    public int add(int val) {
        if(pq.size()>=k && (int)pq.peek() < val){
            pq.add(val);
            pq.poll();
        }else if(pq.size()<k) pq.add(val);

        return (int)pq.peek();
    }
}
