package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoundaryOrderTraversal {
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

    /**
     * Leetcode 545
     * Create Leftboundary by going only in left direction , putting a current node to refer each left child and add it in leftBoudary list .
     * Similarly Create RightBoundary by pointing current to right and keep going in right untill current is null. From each of these list make sure to remove last element .
     * Create a list for leaves and use DFS to find this list. At last left+Leaves+right will give you boundary.
     *
     * @param root
     * @return
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        else if (root.left == null && root.right == null) {
            results.add(root.val);
            return results;
        }

        List<Integer> left = new ArrayList<>();
        left.add(root.val);
        List<Integer> leaves = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        TreeNode current;
        if (root.left != null) {
            current = root.left;
            while ((current != null)) {
                left.add(current.val);
                if (current.left != null) current = current.left;
                else current = current.right;
            }
        }
        if (left.size() > 1) left.remove(left.size() - 1);
        if (root.right != null) {
            current = root.right;
            while ((current != null)) {
                right.add(current.val);
                if (current.right != null) current = current.right;
                else current = current.left;
            }
        }
        if (right.size() > 0) right.remove(right.size() - 1);
        dfs(leaves, root);
        results.addAll(left);
        results.addAll(leaves);
        Collections.reverse(right);
        results.addAll(right);
        return results;

    }

    private void dfs(List<Integer> results, TreeNode root) {

        if (root.left == null && root.right == null) {
            results.add(root.val);
        }
        if (root.left != null) dfs(results, root.left);
        if (root.right != null) dfs(results, root.right);
    }
}
