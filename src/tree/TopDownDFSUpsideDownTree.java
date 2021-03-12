package tree;

public class TopDownDFSUpsideDownTree {
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

    TreeNode[] result = new TreeNode[1];

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        if (root == null) return null;
        helperDFS(root, null, root.right);
        return result[0];
    }

    private void helperDFS(TreeNode root, TreeNode parent, TreeNode rightNode) {

        TreeNode oldRight = root.right;
        TreeNode oldLeft = root.left;
        root.right = parent;
        root.left = rightNode;
        if (oldLeft == null && oldRight == null) {
            result[0] = root;
        }
        if (oldLeft != null) {
            helperDFS(oldLeft, root, oldRight);
        }
    }
}
