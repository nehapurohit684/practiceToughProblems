package adt;

public class MyCircularQueue {

    int[] arr;
    int maxSize;
    int head;
    int tail;

    public MyCircularQueue(int k) {
        arr = new int[k];
        maxSize = k;
        head = 0;
        tail = 0;
    }

    public boolean enQueue(int value) {
        if (tail == maxSize) {
            return false;
        } else {
            arr[(head + tail) % maxSize] = value;
            tail++;
            return true;
        }
    }

    public boolean deQueue() {
        if (tail == 0) return false;
        head = (head + 1) % maxSize;
        tail--;
        return true;
    }

    public int Front() {
        if (tail == 0) return -1;
        return arr[head];

    }

    public int Rear() {
        if (tail == 0) return -1;
        return arr[(head + tail - 1) % maxSize];
    }

    public boolean isEmpty() {

        return tail == 0;

    }

    public boolean isFull() {
        return tail == maxSize;

    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */