package adt;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minSack;


    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minSack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minSack.isEmpty() || val <= minSack.peek()) minSack.push(val);
    }

    public void pop() {
        if (stack.peek().equals(minSack.peek())) {
            minSack.pop();
        }
        stack.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minSack.peek();

    }


}
