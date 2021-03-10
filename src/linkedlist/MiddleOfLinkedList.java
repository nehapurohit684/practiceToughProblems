package linkedlist;

public class MiddleOfLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode[] A = new ListNode[100];
        ListNode current = head;
        int index = 0;
        while (current != null) {
            A[index++] = current;
            current = current.next;
        }
        return A[index / 2];
    }

    public ListNode middleNode2(ListNode head) {
        ListNode current = head;
        ListNode currentD = head;
        while (currentD != null && currentD.next != null) {
            current = current.next;
            currentD = currentD.next.next;
        }
        return current;
    }
}
