package tree;

import java.util.*;

public class DuplicateSubtree {
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

    Map<String, List<TreeNode>> map;
    Set<TreeNode> results;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        serialize(root);
        results = new HashSet<>();
        List<TreeNode> aList = new ArrayList<>();
        for (TreeNode x : results)
            aList.add(x);
        return aList;

    }

    private String serialize(TreeNode root) {
        if (root == null) return "";

        String mySerial = root.val + "," + serialize(root.left) + "," + serialize(root.right);

        map.putIfAbsent(mySerial, new ArrayList<>());
        map.get(mySerial).add(root);
        if (map.get(mySerial).size() > 1) {
            results.add(map.get(mySerial).get(0));
        }
        return mySerial;

    }


}

