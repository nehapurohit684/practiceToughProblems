package tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFSKSmallestBST {
    static class TreeNode {
        int val;
        TreeNode left_ptr;
        TreeNode right_ptr;

        TreeNode(int _val) {
            val = _val;
            left_ptr = null;
            right_ptr = null;
        }
    }

    ;

    public static int bfsTree(TreeNode node, int i) {
        Queue<TreeNode> list = new LinkedList<>();
        list.add(node);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(i, Collections.reverseOrder());
        // Tree is directed Graph where we know which nodes are visited and which nodes are not
        while (!list.isEmpty()) {
            TreeNode current = list.poll();
            //you can add here for loop for number of nodes in queue at start to do level traversing
            // Within a for loop nodes at same level are processed.
            //you can process node value here like add this in results
            if (maxHeap.size() < i) {
                maxHeap.add(current.val);
            } else {
                maxHeap.add(current.val);
                maxHeap.remove();
            }
            if (current.left_ptr != null) {
                list.offer(current.left_ptr);
            }
            if (current.right_ptr != null) {
                list.offer(current.right_ptr);
            }
        }
        return maxHeap.poll();
    }

    static int kth_smallest_element(TreeNode root, int k) {
        if (root == null) return 0;
        return bfsTree(root, k);

    }


    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        helperDFS(root, maxHeap, k);

        return maxHeap.poll();
    }

    private void helperDFS(TreeNode root, PriorityQueue<Integer> maxHeap, int k) {

        if (root != null) {
            helperDFS(root.left_ptr, maxHeap, k);
        }
        if (maxHeap.size() < k) {
            maxHeap.add(root.val);
        } else {
            maxHeap.add(root.val);
            maxHeap.poll();
        }
        if (root.right_ptr != null) {
            helperDFS(root.right_ptr, maxHeap, k);
        }
    }
}
