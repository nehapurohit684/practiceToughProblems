package tree;

import javafx.util.Pair;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
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

    Map<Integer, ArrayList<Pair<Integer, Integer>>> slate = new HashMap<>();
    int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        helperDFS1(root, 0, 0, results);
        for (int i = minValue; i < maxValue + 1; ++i) {

            Collections.sort(slate.get(i), new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    return p1.getKey() - p2.getKey();
                }
            });

            List<Integer> sortedColumn = new ArrayList();
            for (Pair<Integer, Integer> p : slate.get(i)) {
                sortedColumn.add(p.getValue());
            }
            results.add(sortedColumn);
        }

        return results;
    }

    public void helperDFS1(TreeNode node, int verticalRank, int level, List<List<Integer>> results) {
        minValue = Math.min(minValue, verticalRank);
        maxValue = Math.max(maxValue, verticalRank);
        level = level + 1;
        slate.putIfAbsent(verticalRank, new ArrayList<Pair<Integer, Integer>>());
        //if(slate.containsKey(verticalRank))
        slate.get(verticalRank).add(new Pair(level, node.val));

        if (node.left != null) helperDFS1(node.left, verticalRank - 1, level, results);
        if (node.right != null) helperDFS1(node.right, verticalRank + 1, level, results);

    }
}
