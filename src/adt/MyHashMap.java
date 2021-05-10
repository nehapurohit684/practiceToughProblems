package adt;

import java.util.LinkedHashMap;
import java.util.LinkedList;

class MyHashMap {
    LinkedList<Node>[] map;
    int capacity = 769;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        map = new LinkedList[capacity];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int bucket = key % capacity;
        if (map[bucket] == null) {
            map[bucket] = new LinkedList<>();
            map[bucket].add(new Node(key, value));
        } else {
            for (Node node : map[bucket]) {
                if (node.key == key) {
                    node.val = value;
                    return;
                }
            }
            map[bucket].add(new Node(key, value));
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int bucket = key % capacity;
        if (map[bucket] == null) return -1;
        for (Node node : map[bucket]) {
            if (node.key == key) return node.val;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int bucket = key % capacity;
        if (map[bucket] == null) return;
        Node toRemove = null;
        for (Node node : map[bucket]) {
            if (node.key == key) toRemove = node;
        }
        if (toRemove != null) map[bucket].remove(toRemove);
    }

    class Node {
        public int key;
        public int val;

        Node(int _key, int _val) {
            this.key = _key;
            this.val = _val;
        }
    }

}

