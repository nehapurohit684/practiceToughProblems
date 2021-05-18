package linkedlist;

public class RemoveLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Leet code 203
     * When value matches while going over list you
     * delete the node and update current and pred node pointers
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        sentinel.next = head;
        ListNode current = head;
        ListNode pred = sentinel;
        while (current != null) {
            if (current.val == val) {
                pred.next = current.next;
                pred = pred;
                current = current.next;
            } else {
                pred = current;
                current = current.next;
            }
        }
        head = sentinel.next;
        return head;
    }

    /**
     * Leetcode 83: Same code as before for deleting when value matches, update pred and current
     * pred remains same as the current is deleted
     * current now points to current.next
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        sentinel.next = head;
        ListNode current = head;
        ListNode pred = sentinel;
        while (current != null) {
            if (current.val == pred.val) {
                pred.next = current.next;
                pred = pred;
                current = current.next;
            } else {
                pred = current;
                current = current.next;
            }
        }
        head = sentinel.next;
        return head;
    }

    /**
     * Leetcode 82
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesII(ListNode head) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        sentinel.next = head;
        ListNode current = head;
        ListNode pred = sentinel;
        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                pred.next = current.next;
                current = current.next;
            } else {
                pred = current;
                current = current.next;
            }
        }
        head = sentinel.next;
        return head;
    }

    /**
     * leetcode 19
     * Hint: move leader by n times at first and then start the while loop for deletion
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        ListNode pred = null;//walk forward by n times
        ListNode leader = sentinel;//walk forward by n times
        for (int i = 0; i < n; i++) {
            leader = leader.next;
        }
        ListNode follower = sentinel;
        while (leader != null) {
            leader = leader.next;
            pred = follower;
            follower = follower.next;
        }
        //delete follower
        pred.next = follower.next;
        head = sentinel.next;
        return head;

    }

    /**
     * Leetcode 1474
     * Hint: Two pointer one moves by m and put pred at mth position and then it moves by n
     * then m pointer .next becomes n pointer.next so we removed n nodes after each m nodes
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        ListNode leader = head;
        ListNode pred = sentinel;
        while (leader != null) {
            int mSteps = m;
            while (leader != null && mSteps != 0) {
                pred = leader;
                leader = leader.next;
                mSteps--;
            }
            int nsteps = n;
            while (leader != null && nsteps != 0) {
                leader = leader.next;
                nsteps--;
            }
            //delete all n nodes
            pred.next = leader;
        }
        head = sentinel.next;
        return head;

    }
}
