package tree;

import java.util.Vector;

public class DFSHeightNary {

    static class TreeNode {
        Vector<TreeNode> children = new Vector<TreeNode>(0);

        TreeNode() {
            children.clear();
        }
    }

    ;

    public int heightNary(TreeNode node) {
        int[] result = new int[1];
        helperTopDownDFS(node, -1, result);
        helperBottomUpDFS(node, result);
        return result[0];
    }

    private int helperBottomUpDFS(TreeNode node, int[] result) {
        //Base case
        if (node.children.isEmpty()) return 0;
        //Recursive case
        int height = 0;
        for (TreeNode child : node.children) {
            //Height of any internal node = max height from the node ot any leaf child
            height = Math.max(height, 1 + helperBottomUpDFS(child, result));
        }
        result[0] = Math.max(height, result[0]);
        return height;
    }

    private void helperTopDownDFS(TreeNode node, int edgeHeight, int[] result) {
        edgeHeight = edgeHeight + 1;
        result[0] = Math.max(result[0], edgeHeight);
        for (TreeNode child : node.children) {
            helperTopDownDFS(child, edgeHeight, result);
        }
    }
}
