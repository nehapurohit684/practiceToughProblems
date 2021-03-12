package tree;

public class DFSUniValueBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return false;
        boolean[] result = new boolean[1];
        result[0] = true;
        helperDFS(root, result);
        return result[0];
    }

    public int numUnivalTree(TreeNode root) {
        if (root == null) return 0;
        int[] result = new int[1];
        result[0] = 0;
        helperDFSNUM(root, result);
        return result[0];
    }

    // TO find Number of UniVal trees we use Global Variable as int and dfs returns boolean.
    private boolean helperDFSNUM(TreeNode root, int[] result) {

        if (root.left == null && root.right == null) {
            //leaf level worker is always a unival tree in itsself.
            result[0]++;
            return true;
        }
        boolean leftUnival = false, rightUnival = false, amIUniVal = true;

        if (root.left != null) {
            leftUnival = helperDFSNUM(root.left, result);
            if (!leftUnival | root.val != root.left.val) amIUniVal = false;
        }
        if (root.right != null) {
            rightUnival = helperDFSNUM(root.right, result);
            if (!rightUnival | root.val != root.right.val) amIUniVal = false;
        }
        //Note to find if a node is Unival we cant do left && right
        // instead we check them individually and update as your node might not have left or right but can still be unival
        //if we have a node with UniValue we add it to global sum of uniValue trees
        if (amIUniVal) result[0] = result[0] + 1;
        return amIUniVal;
    }

    //Bottom Up DFS
    //Every node returns of its sub tree is univalue
    //And put it in a global variable to find if whole tree is Unival with an AND condition
    private boolean helperDFS(TreeNode root, boolean[] result) {
        if (root.left == null && root.right == null) {
            return true;
        }
        boolean leftUnival = false, rightUnival = false, amIUniVal = true;

        if (root.left != null) {
            leftUnival = helperDFS(root.left, result);
            if (!leftUnival | root.val != root.left.val) amIUniVal = false;
        }
        if (root.right != null) {
            rightUnival = helperDFS(root.right, result);
            if (!rightUnival | root.val != root.right.val) amIUniVal = false;
        }
        //Note to find if a node is Unival we cant do left && right
        // instead we check them individually and update as your node might not have left or right but can still be unival
        result[0] = result[0] && (amIUniVal);
        return amIUniVal;
    }


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

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int[] result = new int[1];
        helperDFSForPath(root, result);
        return result[0];
    }

    private int helperDFSForPath(TreeNode root, int[] result) {
        int path = 0;
        if (root.left == null && root.right == null) {
            return 0;
        }

        int left = 0, right = 0;
        if (root.left != null) {
            int leftH = helperDFSForPath(root.left, result);
            if (root.val == root.left.val) left = leftH + 1;
        }
        if (root.right != null) {
            int rightH = helperDFSForPath(root.right, result);
            if (root.val == root.right.val) right = rightH + 1;
        }
        path = Math.max(left + right, path);
        result[0] = Math.max(result[0], path);
        return Math.max(left, right);
    }
}
