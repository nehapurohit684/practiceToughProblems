package adt;

import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue {

    Queue<Integer> queue;
    Queue<Integer> maxQueue;

    public MaxQueue(int k) {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public void enQueue(int value) {
        queue.offer(value);
        if (!maxQueue.isEmpty() && maxQueue.peek() < value) {
            maxQueue.poll();
        }
        maxQueue.offer(value);
    }

    public int deQueue() {
        int i = queue.remove();
        if (maxQueue.peek() == i) maxQueue.poll();
        return i;
    }
}
