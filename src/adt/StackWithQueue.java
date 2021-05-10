package adt;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithQueue {

    Queue<Integer> oneQueue;
    int top;

    /**
     * initialize your data structure here.
     */
    public StackWithQueue() {
        oneQueue = new LinkedList<>();
    }

    public void push(int val) {
        oneQueue.offer(val);
        top = val;

    }

    public int pop() {
        for (int i = 0; i < oneQueue.size() - 1; i++) {
            push(oneQueue.poll());
        }
        return oneQueue.poll();
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return oneQueue.isEmpty();
    }
}
