package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void bfsTree(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(node);
        // Tree is directed Graph where we know which nodes are visited and which nodes are not
        while (!list.isEmpty()) {
            TreeNode current = list.poll();
            //you can add here for loop for number of nodes in queue at start to do level traversing
            // Within a for loop nodes at same level are processed.
            //you can process node value here like add this in results
            if (current.left != null) {
                list.offer(current.left);
            }
            if (current.right != null) {
                list.offer(current.right);
            }
        }
    }
}
