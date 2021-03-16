package tree;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
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

    List<Integer> inOrder = new ArrayList<>();
    int count = 0;

    public BSTIterator(TreeNode root) {
        helperDFS(root);
    }

    private void helperDFS(TreeNode root) {
        if (root.left != null) helperDFS(root.left);
        inOrder.add(root.val);
        if (root.right != null) helperDFS(root.right);
    }

    public int next() {
        return inOrder.get(count++);
    }

    public boolean hasNext() {
        return count < inOrder.size();
    }
}
