package adt;

import java.util.Stack;

public class QueueWithStack {

    Stack<Integer> enQueue = new Stack<>();
    Stack<Integer> deQueue = new Stack<>();

    public QueueWithStack() {

    }

    public void enQueue(int value) {
        enQueue.push(value);
    }

    public int deQueue() {
        if (deQueue.size() >= 0) return deQueue.pop();
        while (!enQueue.isEmpty()) deQueue.push(enQueue.pop());
        return deQueue.pop();
    }
}
