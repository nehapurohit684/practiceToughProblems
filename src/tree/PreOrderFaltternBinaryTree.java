package tree;

public class PreOrderFaltternBinaryTree {

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

    public void flatten(TreeNode root) {
        TreeNode sentinel = new TreeNode();
        postOrderDFS(root);
        root = sentinel.left;
        TreeNode curr = root;
        while (curr != null) {
            curr.right = curr.left;
            curr = curr.left;
        }
    }

    /**
     * To do pointer manipulation on any node you need to know its right and left node
     * so we actually process root after calling left dfs and right dfs
     * for each node we point its last element of lefttail to its right and make left point to right
     * Each node after falttening return the right most Tail, and make left node =null,
     * For a node where there is no righttail at all we reutrn leftTail
     *
     * @param root
     * @return
     */
    private TreeNode postOrderDFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode leftTail = postOrderDFS(root.left);
        TreeNode rightTail = postOrderDFS(root.right);


        if (leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        return rightTail;
    }
}
