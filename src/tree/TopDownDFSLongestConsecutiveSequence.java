package tree;

import java.util.Map;

public class TopDownDFSLongestConsecutiveSequence {
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
     * Given a binary tree, find the length of the longest consecutive sequence path.
     * Hint: You need to pass parentvalue in dfs to check if consecutive numbers exist or not
     *
     * @param root
     * @return
     */
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int[] result = new int[1];
        dfsHelper(root, root.val, 1, result);
        return result[0];
    }

    private void dfsHelper(TreeNode root, int val, int lengthSofar, int[] result) {
        if (root.val == val + 1) {
            lengthSofar++;
        } else {
            //if its not consecutive number we start a new sequence
            lengthSofar = 1;
        }
        result[0] = Math.max(result[0], lengthSofar);
        if (root.left != null)
            dfsHelper(root.left, root.val, lengthSofar, result);
        if (root.right != null)
            dfsHelper(root.right, root.val, lengthSofar, result);

    }
}