package linkedlist;

import java.util.List;

public class CycleInLInkedList {

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode node = null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                node = head;
                while (node != slow) {
                    node = node.next;
                    slow = slow.next;
                }
                return node;
            }
        }
        return null;
    }
}
