package linkedlist;

public class InsertionSort {

    public ListNode insertionSortList(ListNode head) {

        ListNode sentinel = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;
        while (current != null) {
            ListNode succ = current.next;
            current.next = null;
            ListNode pred = sentinel;
            ListNode current2 = pred.next;
            while (current2 != null && current2.val < current.val) {
                pred = pred.next;
                current2 = current2.next;
            }
            pred.next = current;
            current.next = current2;
            current = succ;
        }
        return sentinel.next;
    }
}
