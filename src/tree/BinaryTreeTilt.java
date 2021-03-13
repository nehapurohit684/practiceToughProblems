package tree;

public class BinaryTreeTilt {
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

    public int findTilt(TreeNode root) {
        int[] result = new int[1];
        helperDFS(root, result);
        return result[0];
    }

    private int helperDFS(TreeNode node, int[] result) {
        int myleftSum = 0, myrightSum = 0;
        int myTilt = 0;

        if (node.left != null) {
            myleftSum = myleftSum + helperDFS(node.left, result);
        }
        if (node.right != null) {
            myrightSum = myrightSum + helperDFS(node.right, result);
        }
        myTilt = Math.abs(myleftSum - myrightSum);
        result[0] += myTilt;
        return myleftSum + myrightSum + node.val;

    }
}
