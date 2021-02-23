package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * <p>
 * A leaf is a node with no children.
 * 2. Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
 */
public class DFSPathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        boolean[] result = new boolean[1];
        helperDFS(root, targetSum, result);
        return result[0];
    }

    //Top Down DFS
    private void helperDFS(TreeNode root, int targetSum, boolean[] result) {
        //Base Case
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                result[0] = true;
                return;
            }
        }
        //Recursive case
        if (root.left != null) {
            helperDFS(root.left, targetSum - root.val, result);
        }
        if (root.right != null) {
            helperDFS(root.right, targetSum - root.val, result);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        helperDFS2(root, new ArrayList<>(), results, targetSum);
        return results;
    }

    // Top Dowm DFS
    private void helperDFS2(TreeNode root, ArrayList<Integer> slate, List<List<Integer>> results, int targetSum) {
        //base case
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                slate.add(root.val);
                results.add(new ArrayList<>(slate));
                slate.remove(slate.size() - 1);
                return;
            }
        }

        //recursive case
        if (root.left != null) {
            slate.add(root.val);
            helperDFS2(root.left, slate, results, targetSum - root.val);
            slate.remove(slate.size() - 1);
        }
        if (root.right != null) {
            slate.add(root.val);
            helperDFS2(root.right, slate, results, targetSum - root.val);
            slate.remove(slate.size() - 1);
        }
    }

    //Definition for a binary tree node.
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
}
