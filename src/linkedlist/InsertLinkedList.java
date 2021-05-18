package linkedlist;

public class InsertLinkedList {
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;

    /**
     * Leetcode 708
     * head is not pointing to smallest or largets element
     * SO first we need head to point to smallest element
     * for insertion in circular list we need atleast two nodes, we handle list whose size is 0 and 1 seperately
     * Then to find smallest element in the circular list we need two ptrs to go over all elements pred and current
     * we cant use head here because we want to check current!=head in while loop to make sure we are not going round and round in circle
     * Then once you have pointer pointing to smallest element then
     * you can insert value with another while loop which check if current.val< insertVal then keep moving forward
     * Once you know where newNode will go you do two things to isert in circular list
     * pred.next = newNode and newNode.next =curr
     * Also you need to handle edge case where new value is bigger than biggest or smaller that smallest seperately
     * Because if inserted value is greater than largets value in the list while loop with cur.val < insertedVal will run infinite
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        //size = 0
        Node newNode = new Node(insertVal);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        //size = 1
        if (head.next == head) {
            newNode.next = head;
            head.next = newNode;
            return head;
        }

        Node pred = head;
        Node curr = head.next;
        //find curr point to smallest
        //curr is smallest and pred will be largest
        while (curr.val >= pred.val && curr != head) {
            pred = pred.next;
            curr = curr.next;
        }
        //you will have to handle this case when insert value is larger than largest value
        // and smaller than smallest value
        if (insertVal > pred.val || insertVal < curr.val) {
            pred.next = newNode;
            newNode.next = curr;
            return head;
        }
        while (curr.val < insertVal) {
            pred = curr;
            curr = curr.next;
        }
        pred.next = newNode;
        newNode.next = curr;
        return head;
    }
}
