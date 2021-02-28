package tree;

public class BottomUPValidateBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return helpderDFS(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean helpderDFS(TreeNode node, long maxValue, long minValue) {
        boolean isBST = true;
        boolean leftBST = false, rightBST = false;
        if (node.val >= maxValue || node.val <= minValue) isBST = false;
        if (node.left != null) {
            leftBST = helpderDFS(node.left, node.val, minValue);
            if (!leftBST) isBST = false;
        }
        if (node.right != null) {
            rightBST = helpderDFS(node.right, maxValue, node.val);
            if (!rightBST) isBST = false;
        }
        return isBST;
    }
}
