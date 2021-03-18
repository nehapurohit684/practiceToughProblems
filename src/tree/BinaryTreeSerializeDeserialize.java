package tree;

import java.util.*;

public class BinaryTreeSerializeDeserialize {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * This code is similar to serialize BST but here you have to create pre order and in order sequence seperately
     * because for binary tree its not required for inorder to be sorted
     * Also this code doesnt work on duplicate values in the tree
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder preOrder = new StringBuilder();
        StringBuilder inOrder = new StringBuilder();
        helperDFS(root, preOrder, inOrder);

        return preOrder + "$" + inOrder;


    }

    private Map<Integer, Integer> generateMap(List<Integer> inOrder) {
        Map<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < inOrder.size(); i++) {
            results.put(inOrder.get(i), i);
        }
        return results;
    }

    private void helperDFS(TreeNode root, StringBuilder preOrder, StringBuilder inOrder) {
        if (root == null) return;
        preOrder.append(root.val + ",");
        helperDFS(root.left, preOrder, inOrder);
        inOrder.append(root.val + "-");
        helperDFS(root.right, preOrder, inOrder);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String preOrd = data.substring(0, data.indexOf("$") - 1);
        String inOrd = data.substring(data.indexOf("$") + 1);

        List<Integer> preOrder = generateArray(preOrd);
        List<Integer> inOrder = generateArray(inOrd);
        Map<Integer, Integer> inOrderMap = generateMap(inOrder);

        return helperTreeConstruction(preOrder, 0, preOrder.size() - 1, inOrderMap, 0, inOrder.size() - 1);

    }


    private TreeNode helperTreeConstruction(List<Integer> preOrder, int startP, int endP,
                                            Map<Integer, Integer> inOrderMap, int startI, int endI) {
        if (startP > endP) return null;
        if (startP == endP) return new TreeNode(preOrder.get(startP));

        TreeNode root = new TreeNode(preOrder.get(startP));
        int nodeIndexRoot = inOrderMap.get(preOrder.get(startP));
        int nodeLeft = nodeIndexRoot - startI;
        root.left = helperTreeConstruction(preOrder, startP + 1, startP + nodeLeft, inOrderMap, startI, nodeIndexRoot - 1);
        root.right = helperTreeConstruction(preOrder, startP + nodeLeft + 1, endP, inOrderMap, nodeIndexRoot + 1, endI);
        return root;
    }

    private List<Integer> generateArray(String data) {
        List<Integer> nums = new ArrayList<>();
        for (String s : data.split(",")) {
            nums.add(Integer.valueOf(s));
        }
        return nums;
    }
}
