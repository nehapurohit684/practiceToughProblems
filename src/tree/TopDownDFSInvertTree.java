package tree;

public class TopDownDFSInvertTree {
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

    public TreeNode invertTree(TreeNode root) {

        if (root == null) return null;
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode tempRight = root.right;
        TreeNode tempLeft = root.left;
        root.left = tempRight;
        root.right = tempLeft;
        if (root.left != null) invertTree(root.left);
        if (root.right != null) invertTree(root.right);
        return root;
    }
}
