package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTPreOrderTreeConstruction {

    /**
     * 297. Serialize and Deserialize Binary Tree
     **/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder preOrder = new StringBuilder();
        helperDFS(root, preOrder);

        return preOrder.toString();


    }


    private void helperDFS(TreeNode root, StringBuilder preOrder) {
        if (root == null) return;
        preOrder.append(root.val + ",");
        if (root.left != null) {
            helperDFS(root.left, preOrder);
        } else {
            preOrder.append("null" + ",");
        }
        if (root.right != null) {
            helperDFS(root.right, preOrder);
        } else {
            preOrder.append("null" + ",");
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<String> preOrder = generateArray(data);
        return helperTreeConstruction(preOrder);

    }


    private TreeNode helperTreeConstruction(Queue<String> preOrder) {
        if (preOrder.size() == 0) return null;
        if (preOrder.size() == 1) {
            String val = preOrder.poll();
            if (val.equalsIgnoreCase("null")) return null;
            else return new TreeNode(Integer.valueOf(val));
        }

        String valRoot = preOrder.poll();
        if (valRoot.equalsIgnoreCase("null")) return null;
        else {
            TreeNode root = new TreeNode(Integer.valueOf(valRoot));
            root.left = helperTreeConstruction(preOrder);
            root.right = helperTreeConstruction(preOrder);
            return root;
        }
    }

    private Queue<String> generateArray(String data) {
        Queue<String> nums = new LinkedList<>();
        for (String s : data.split(",")) {
            nums.add(s);
        }
        return nums;
    }
}
