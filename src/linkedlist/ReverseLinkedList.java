package linkedlist;

public class ReverseLinkedList {

    ListNode globalHead = null;

    public ListNode reverseList(ListNode head) {

        if (head == null) return null;
        helper(head);
        return globalHead;

    }

    //Bottom up
    private ListNode helper(ListNode h) {
        if (h.next == null) {
            globalHead = h;
            return h;
        }
        //recursive case
        ListNode tail = helper(h.next);
        h.next = null;
        tail.next = h;
        return h;
    }

    // leetcode 92
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode sentinel = new ListNode(Integer.MIN_VALUE);
        ListNode curr = head;
        ListNode pred = sentinel;
        sentinel.next = head;
        int index = 1;

        while (index != left) {
            pred = curr;
            curr = curr.next;
            index++;
        }
        pred.next = null;
        //save left and starting pointer for listnode
        ListNode tail_left = pred;
        ListNode head_middle = curr;
        pred = null;
        while (index != right + 1) {
            ListNode succ = head_middle.next;
            head_middle.next = pred;
            pred = head_middle;
            head_middle = succ;
            index++;
        }
        tail_left.next = pred;
        curr.next = head_middle;

        return sentinel.next;

    }
}
