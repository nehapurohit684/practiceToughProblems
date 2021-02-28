package tree;

import javafx.util.Pair;

public class BottomUpDFSLCA {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode result = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        helperDFS(root, p, q);
        return result;
    }

    private Pair<Boolean, Boolean> helperDFS(TreeNode node, TreeNode p, TreeNode q) {
        boolean pfound = false, qfound = false;

        if (node == p) pfound = true;
        if (node == q) qfound = true;
        //Base case pass
        //recursive case
        if (node.left != null) {
            Pair<Boolean, Boolean> left = helperDFS(node.left, p, q);
            pfound = pfound | left.getKey();
            qfound = qfound | left.getValue();
        }
        if (node.right != null) {
            Pair<Boolean, Boolean> left = helperDFS(node.right, p, q);
            pfound = pfound | left.getKey();
            qfound = qfound | left.getValue();
        }
        if (pfound && qfound && result == null) result = node;
        return new Pair<>(pfound, qfound);
    }
}
