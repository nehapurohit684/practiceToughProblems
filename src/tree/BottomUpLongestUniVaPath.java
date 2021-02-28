package tree;

public class BottomUpLongestUniVaPath {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int[] result = new int[1];
        helperDFS(root, result);
        return result[0];
    }

    private int helperDFS(TreeNode root, int[] result) {
        int path = 0;
        if (root.left == null && root.right == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (root.left != null) {
            int leftH = helperDFS(root.left, result);
            if (root.val == root.left.val) left = leftH + 1;
        }
        if (root.right != null) {
            int rightH = helperDFS(root.right, result);
            if (root.val == root.right.val) right = rightH + 1;
        }
        path = Math.max(left + right, path);
        result[0] = Math.max(result[0], path);
        return Math.max(left, right);
    }
}
