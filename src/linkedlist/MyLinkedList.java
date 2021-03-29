package linkedlist;

public class MyLinkedList {
    ListNode head;
    ListNode tail;


    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    /**
     * Start with index zero and traverse the list
     *
     * @param index
     * @return
     */
    public int get(int index) {
        ListNode current = head;
        int nodeIndex = 0;
        while (current != null && nodeIndex != index) {
            current = current.next;
            nodeIndex++;
        }
        if (current != null) return current.val;
        else return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode();
        node.val = val;
        node.next = head;
        head = node;
        if (tail == null) tail = node;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode();
        node.val = val;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    /**
     * For insertion and deletion in a list we always use sentinel ndoe as it na chnage head.
     *
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        ListNode current = head;
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        ListNode pred = sentinel;
        int nodeIndex = 0;
        while (current != null && nodeIndex != index) {
            pred = current;
            current = current.next;
        }
        if (current != null || nodeIndex == index) {
            ListNode newNode = new ListNode();
            newNode.val = val;
            pred.next = newNode;
            newNode.next = current;
        }
        head = sentinel.next;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        ListNode current = head;
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        ListNode pred = sentinel;
        int nodeIndex = 0;
        while (current != null && nodeIndex != index) {
            pred = current;
            current = current.next;
        }
        if (current != null) pred.next = current.next;
        head = sentinel.next;

    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

