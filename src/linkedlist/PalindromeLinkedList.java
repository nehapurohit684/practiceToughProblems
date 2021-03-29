package linkedlist;

public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }
        if (head != null && head.next != null && head.next.next == null && head.val == head.next.val) return true;
        if (head != null && head.next != null && head.next.next == null && head.val != head.next.val) return false;

        ListNode hare = head;
        ListNode tortoise = head;

        while (hare.next != null && hare.next.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        //reverse right half list break it and then compare left and right list
        ListNode node = tortoise.next;
        tortoise.next = null;
        ListNode pred = null;
        //reverse node
        while (node != null) {
            ListNode succ = node.next;
            node.next = pred;
            pred = node;
            node = succ;
        }
        //reverse list again to maintain the original list clean up
        node = pred;
        pred = null;
        ListNode left = head;
        boolean result = true;
        while (node != null) {
            if (left.val != node.val) {
                result = result && false;
            }
            ListNode succ = node.next;
            node.next = pred;
            pred = node;
            node = succ;
            left = left.next;
        }
        tortoise.next = pred;

        return result;
    }
}
