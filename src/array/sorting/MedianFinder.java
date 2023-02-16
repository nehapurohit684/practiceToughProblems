package array.sorting;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;


    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            if (minHeap.peek() < num) {
                minHeap.add(num);
            } else if (maxHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
        }
        while (maxHeap.size() - minHeap.size() >= 2) {
            int val = maxHeap.poll();
            minHeap.add(val);
        }
        while (minHeap.size() - maxHeap.size() >= 2) {
            int val = minHeap.poll();
            maxHeap.add(val);
        }
    }

    public double findMedian() {

        if (minHeap.size() == maxHeap.size()) return (double)(minHeap.peek() + maxHeap.peek()) / 2;
        else if (minHeap.size() == 1 + maxHeap.size()) return (double) minHeap.peek();
        else return (double) maxHeap.peek();
    }
}