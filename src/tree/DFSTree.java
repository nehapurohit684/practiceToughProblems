package tree;

public class DFSTree {

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

    public void dfsTree(TreeNode node) {
        if (node == null) return;
        dfsTree(node.left);
        dfsTree(node.right);
    }

    /**
     * In this template dfs is not called on a null node
     * you do null check at calling method.
     *
     * @param node
     */
    public void dfsTreeBetter(TreeNode node) {
        //Base Case: left right null indicates leaf node ,, but null node can be a node of a leaf
        if (node.left == null && node.right == null) {
            //do something
        }
        //Recursive Case
        if (node.left != null) dfsTree(node.left);
        if (node.right != null) dfsTree(node.right);
    }
}
