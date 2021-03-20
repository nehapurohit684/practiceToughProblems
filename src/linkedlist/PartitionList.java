package linkedlist;

import javax.swing.*;

public class PartitionList {

    public ListNode partition(ListNode head, int x) {

        ListNode leftsentinel = new ListNode(Integer.MIN_VALUE);
        ListNode rightsentinel = new ListNode(Integer.MIN_VALUE);
        ListNode left = leftsentinel;
        ListNode right = rightsentinel;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
                head = head.next;
                left.next = null;
            } else {
                right.next = head;
                right = right.next;
                head = head.next;
                right.next = null;
            }
        }

        left.next = rightsentinel.next;
        return leftsentinel.next;
    }
}
