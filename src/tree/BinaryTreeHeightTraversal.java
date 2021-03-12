package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeHeightTraversal {
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

    Map<Integer, List<Integer>> slateSorted = new HashMap<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        helperDFS(root);
        for (int key : slateSorted.keySet()) {
            results.add(slateSorted.get(key));
        }

        return results;

    }

    private int helperDFS(TreeNode node) {
        int myHeight = 0;

        int leftH = 0, rightH = 0;
        if (node.left != null) {
            leftH = helperDFS(node.left);
        }
        if (node.right != null) {
            rightH = helperDFS(node.right);
        }
        myHeight = Math.max(leftH, rightH) + 1;
        slateSorted.putIfAbsent(myHeight, new ArrayList<>());
        slateSorted.get(myHeight).add(node.val);

        return myHeight;
    }
}
