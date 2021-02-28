package tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 */
public class DFSDiameterBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int[] result = new int[1];
        helperDFS(root, result);
        return result[0];

    }

    //Bottom UP DFS
    //Every node finding its local max diameter and also sending it height to parent.
    //Time and Space Complexity is Order N.
    //bottom up DFS
    private int helperDFS(TreeNode root, int[] result) {
        //Base case
        if (root.left == null && root.right == null) {
            return 0;
        }
        //Recursive case
        int left = 0, right = 0, myDia = 0;
        if (root.left != null) {
            left = helperDFS(root.left, result) + 1;
        }
        if (root.right != null) {
            right = helperDFS(root.right, result) + 1;
        }
        myDia = Math.max(left + right, myDia);
        result[0] = Math.max(myDia, result[0]);
        // Return your height to parent
        return Math.max(left, right);
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
