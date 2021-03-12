package tree;

public class BottomUpHeightOfTree {
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

    boolean result = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        helperDFS(root);
        return result;
    }

    private int helperDFS(TreeNode root) {
        int localLeftHeight = 0;
        int localRightHeight = 0;

        //base case
        if (root.left == null && root.right == null) {
            return 0;
        }
        if (root.left != null) {
            localLeftHeight = helperDFS(root.left) + 1;
        }
        if (root.right != null) {
            localRightHeight = helperDFS(root.right) + 1;
        }
        if (Math.abs(localLeftHeight - localRightHeight) > 1) result = false;

        return Math.max(localLeftHeight, localRightHeight);
    }
}
