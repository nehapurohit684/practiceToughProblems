package tree;

import java.util.*;

public class DFSLevelOrderProcessing {
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

    /**
     * 513: Given the root of a binary tree, return the leftmost value in the last row of the tree.
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {

        int[] result = new int[1];

        helperDFS(root, new ArrayList<>(), result, 0);
        return result[0];
    }

    public void helperDFS(TreeNode root, List<Integer> slate, int[] result, int depth) {
        int mydepth = depth + 1;
        //we are adding values in slate as we are going deeper
        //if mydepth is one and slate size is also 1 that means my parent has added left most in slate already
        if (mydepth > slate.size()) {
            slate.add(root.val);
        }
        if (root.left != null) helperDFS(root.left, slate, result, mydepth);
        if (root.right != null) helperDFS(root.right, slate, result, mydepth);

        result[0] = slate.get(slate.size() - 1);
    }

}
