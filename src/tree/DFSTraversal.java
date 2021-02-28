package tree;

import java.util.ArrayList;
import java.util.List;

public class DFSTraversal {

    private static class TreeNode {
        public int val;
        public TreeNode left_ptr;
        public TreeNode right_ptr;
    }

    /**
     * Given a binary tree, return all paths from root to leaf.
     *
     * @param root
     * @return
     */
    static List<List<Integer>> allPathsOfABinaryTree(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        dfsHelper(root, new ArrayList<>(), results);
        return results;
    }

    private static void dfsHelper(TreeNode root, ArrayList<Integer> slate, List<List<Integer>> results) {

        //Base case
        if (root.left_ptr == null && root.right_ptr == null) {
            slate.add(root.val);
            results.add(new ArrayList<>(slate));
            slate.remove(slate.size() - 1);
            return;
        }

        //recursive case
        slate.add(root.val);
        if (root.left_ptr != null) dfsHelper(root.left_ptr, slate, results);
        if (root.right_ptr != null) dfsHelper(root.right_ptr, slate, results);
        slate.remove(slate.size() - 1);

    }


}
