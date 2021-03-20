package linkedlist;

import javafx.util.Pair;
import org.w3c.dom.ls.LSInput;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeSortList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode sentinel = new ListNode();
        ListNode tail = sentinel;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
                tail.next = null;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
                tail.next = null;
            }
        }

        if (l1 != null) tail.next = l1;
        else tail.next = l2;
        return sentinel.next;
    }

    public ListNode sortList(ListNode head) {

        return helperSort(head);

    }

    private ListNode helperSort(ListNode h) {
        if (h == null || h.next == null) return h;
        if (h.next != null && h.next.next == null) {
            if (h.val < h.next.val) return h;
            else {
                ListNode node = h;
                h = h.next;
                node.next = null;
                h.next = node;
                return h;
            }
        }

        ListNode hare = h;
        ListNode tortoise = h;

        while (hare != null && hare.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        //find the mid point and the break the listnodes
        ListNode breakPoint = tortoise.next;
        tortoise.next = null;

        ListNode l1 = helperSort(h);
        ListNode l2 = helperSort(breakPoint);

        return mergeTwoLists(l1, l2);
    }

    /**
     * use Priority Quque to add pointer to first node of each list and it value ,
     * extract min value each time and ass it in PQ  keep adding polled node to tails next
     * and the at last return sentinel.next
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<Pair<Integer, ListNode>> pq = new PriorityQueue<Pair<Integer, ListNode>>(new Comparator<Pair<Integer, ListNode>>() {
            @Override
            public int compare(Pair<Integer, ListNode> t1, Pair<Integer, ListNode> t2) {
                if (t1.getKey() < t2.getKey()) return -1;
                if (t1.getKey() > t2.getKey()) return +1;
                return 0;
            }
        });
        for (ListNode list : lists) {
            if (list != null)
                pq.add(new Pair<>(list.val, list));
        }
        ListNode sentinel = new ListNode(Integer.MAX_VALUE);
        ListNode tail = sentinel;
        while (!pq.isEmpty()) {
            Pair<Integer, ListNode> node = pq.poll();
            ListNode curren = node.getValue();
            tail.next = curren;
            tail = curren;
            curren = curren.next;
            tail.next = null;

            if (curren != null)
                pq.add(new Pair<>(curren.val, curren));
        }
        return sentinel.next;
    }
}
