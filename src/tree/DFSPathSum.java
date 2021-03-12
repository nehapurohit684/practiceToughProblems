package tree;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * <p>
 * A leaf is a node with no children.
 * 2. Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
 * 3. Find the number of paths that sum to a given value.The path does not need to start or end at the root or a leaf,
 * but it must go downwards-- Hint: At any node you need value of all parents and see if the sum of all parents+ sum = target
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

    public int pathSumCount(TreeNode root, int sum) {

        int[] result = new int[1];
        helperDFS3(root, sum, new ArrayList<>(), result);
        return result[0];
    }

    private void helperDFS3(TreeNode root, int sum, List<Integer> slate, int[] result) {
        slate.add(root.val);
        int sumSoFar = 0;
        //you are maintaining sub Array sum from node to its parent
        //checking sum of current node and its subaaray from node to any of its parent = target
        for (int i = slate.size() - 1; i > 0; i--) {
            sumSoFar += slate.get(i);
            if (sum == sumSoFar) {
                result[0]++;
            }
        }
        if (root.left != null) helperDFS3(root.left, sum, slate, result);
        if (root.right != null) helperDFS3(root.right, sum, slate, result);
        slate.remove(slate.size() - 1);
    }

    /**
     * 124 : Given the root of a binary tree, return the maximum path sum of any path.
     * Here we would have to use Bottom UP DFS as each child needs to return max path sum to its parents passes through child
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {

        int[] result = new int[1];
        Arrays.fill(result, Integer.MIN_VALUE);
        helperDFSBottomUp(root, result);
        return result[0];
    }

    /*
    For every Node Local solution is to find maxpathsum via that node and maxpath for parent.
    MaxPath for parent is = Max(node, node+right, node+left)
    MaxPathSumViaNode is max(node.val,node+left,node+right,node+left+right)
     */
    private int helperDFSBottomUp(TreeNode root, int[] result) {
        int maxPathSumViaMe = root.val;
        int leftpathsum = 0, rightpathsum = 0;
        //recursive case
        if (root.left != null) {
            leftpathsum = helperDFSBottomUp(root.left, result);

        }
        if (root.right != null) {
            rightpathsum = helperDFSBottomUp(root.right, result);
        }
        maxPathSumViaMe = Math.max(Math.max(root.val, leftpathsum + root.val), Math.max(rightpathsum + root.val, leftpathsum + rightpathsum + root.val));
        result[0] = Math.max(maxPathSumViaMe, result[0]);

        return Math.max(Math.max(leftpathsum + root.val, rightpathsum + root.val), root.val);
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
