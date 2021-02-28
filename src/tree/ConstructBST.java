package tree;

public class ConstructBST {

    static class TreeNode {
        int val;
        TreeNode left_ptr;
        TreeNode right_ptr;

        TreeNode(int _val) {
            val = _val;
            left_ptr = null;
            right_ptr = null;
        }
    }

    static TreeNode build_balanced_bst(int[] a) {
        return helperBuildTree(a, 0, a.length - 1);
    }

    private static TreeNode helperBuildTree(int[] a, int start, int end) {

        if (start == end) return new TreeNode(a[start]);
        if (start > end) return null;

        int mid = (start + (end - start)) / 2;
        TreeNode current = new TreeNode(a[mid]);
        current.left_ptr = helperBuildTree(a, start, mid - 1);
        current.right_ptr = helperBuildTree(a, mid + 1, end);

        return current;
    }
}
