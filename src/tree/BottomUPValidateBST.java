package tree;

import javafx.util.Pair;

public class BottomUPValidateBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    boolean result = true;
    public boolean isValidBST(TreeNode root) {
        helpderDFS(root, Long.MAX_VALUE, Long.MIN_VALUE);
        return result;
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
        if (!isBST) result = false;
        return isBST;
    }

    /**
     * Each node , returns min, max amIBST and my length to it parent.
     * each node compares if its value is smaller than left's max value or its greater than right's smallest value if yes its not a BST
     * Also if any of its right or left tree is not a BST its not a BST.
     * Remember the my length here doesnt necessarly have to have a v or straight shape it can have BST shape.
     */
    int largest = 0;

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        helper(root);

        return largest;
    }

    private Quad helper(TreeNode root) {

        int mylength = 1;
        boolean amIBST = true;
        Quad left, right;
        int minValue = root.val, maxValue = root.val;
        if (root.left != null) {
            left = helper(root.left);
            mylength = mylength + left.myLength_;
            maxValue = Math.max(maxValue, left.maxValue_);
            minValue = Math.min(minValue, left.minValue_);
            if (!left.isBST_ | root.val <= left.maxValue_) amIBST = false;
        }

        if (root.right != null) {
            right = helper(root.right);
            mylength = mylength + right.myLength_;
            maxValue = Math.max(maxValue, right.maxValue_);
            minValue = Math.min(minValue, right.minValue_);
            if (!right.isBST_ | root.val >= right.minValue_) amIBST = false;
        }
        if (amIBST && mylength > largest) largest = mylength;
        return new Quad(mylength, minValue, maxValue, amIBST);
    }

    public class Quad {
        int myLength_;
        int minValue_;
        int maxValue_;
        boolean isBST_;

        Quad(int myLength, int minValue, int maxValue, boolean isBST) {
            myLength_ = myLength;
            minValue_ = minValue;
            maxValue_ = maxValue;
            isBST_ = isBST;
        }
    }
}
