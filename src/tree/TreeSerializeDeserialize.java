package tree;

import org.w3c.dom.ls.LSInput;

import java.util.*;

public class TreeSerializeDeserialize {


    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    //Tree Traversal
    public String serialize(TreeNode root) {
        StringBuilder results = new StringBuilder();
        if (root == null) return "";
        helperDFSTraversal(root, results);
        return results.toString();
    }

    private void helperDFSTraversal(TreeNode root, StringBuilder results) {
        if (root == null) {
            return;
        }
        results.append(root.val + ",");
        if (root.left != null) helperDFSTraversal(root.left, results);
        if (root.right != null) helperDFSTraversal(root.right, results);
    }

    // Decodes your encoded data to tree.
    //Tree Construction using Pre Order Array
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        List<Integer> preOrder = generateArray(data);
        List<Integer> inOrder = new ArrayList<>();
        for (Integer i : preOrder) {
            inOrder.add(i);
        }
        Collections.sort(inOrder);
        Map<Integer, Integer> inOrderMap = makeHashMap(inOrder);

        return helperTreeConstrcutionPreOrder(preOrder, 0, preOrder.size() - 1, inOrderMap, 0, inOrder.size() - 1);
    }

    private TreeNode helperTreeConstrcutionPreOrder(List<Integer> preOrder, int startP, int endP,
                                                    Map<Integer, Integer> inOrderMap, int startI, int endI) {
        if (startP > endP) return null;
        if (startP == endP) return new TreeNode(preOrder.get(startP));

        TreeNode root = new TreeNode(preOrder.get(startP));
        int nodeIndexRoot = inOrderMap.get(preOrder.get(startP));
        int nodeLeft = nodeIndexRoot - startI;
        root.left = helperTreeConstrcutionPreOrder(preOrder, startP + 1, startP + nodeLeft, inOrderMap, startI, nodeIndexRoot - 1);
        root.right = helperTreeConstrcutionPreOrder(preOrder, startP + nodeLeft + 1, endP, inOrderMap, nodeIndexRoot + 1, endI);
        return root;
    }

    private Map<Integer, Integer> makeHashMap(List<Integer> inOrder) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < inOrder.size(); i++) {
            result.put(inOrder.get(i), i);
        }
        return result;
    }

    private List<Integer> generateArray(String data) {
        List<Integer> nums = new ArrayList<>();
        for (String s : data.split(",")) {
            nums.add(Integer.valueOf(s));
        }
        return nums;
    }

}
