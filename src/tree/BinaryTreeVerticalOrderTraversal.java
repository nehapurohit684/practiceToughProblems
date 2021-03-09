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
        helperDFS1(root, 0, 0);
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
            Collections.sort(sortedColumn);
            results.add(sortedColumn);
        }

        return results;
    }

    /**
     * we'll be putting node in hashMap slate with key as rank and value is pai with level and actual node
     * we are keeping track of keys using min value and Max Value so that we can put node from left to right
     * NOte in BFS we dont need to do any thing to explicitly maintain left to right but in DFS will need level
     *
     * @param node
     * @param verticalRank
     * @param level
     */
    public void helperDFS1(TreeNode node, int verticalRank, int level) {
        minValue = Math.min(minValue, verticalRank);
        maxValue = Math.max(maxValue, verticalRank);
        level = level + 1;
        slate.putIfAbsent(verticalRank, new ArrayList<Pair<Integer, Integer>>());
        //if(slate.containsKey(verticalRank))
        slate.get(verticalRank).add(new Pair(level, node.val));

        if (node.left != null) helperDFS1(node.left, verticalRank - 1, level);
        if (node.right != null) helperDFS1(node.right, verticalRank + 1, level);

    }

    /**
     * to find vertical order Level order traversing in more intutive
     * Vertical rank of root is zero and anything left is -1 to parent and right is +1 to parent
     * In BFS you go left to right and then along wth node save it its rank also -Pre order traversal
     * Before putting node in queue check the value of the node based on + , - or zero put the value in corresponding list
     * Notice Positive and negative will be list of list , zero will be list
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrderBFS(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<Pair<TreeNode, Integer>> list = new LinkedList<>();
        list.add(new Pair<>(root, 0));
        List<List<Integer>> negative = new ArrayList<>();
        List<Integer> zeroL = new ArrayList<>();
        List<List<Integer>> positive = new ArrayList<>();

        while (!list.isEmpty()) {
            Pair<TreeNode, Integer> node = list.poll();
            if (node.getValue() == 0) zeroL.add(node.getKey().val);
            if (node.getValue() > 0) {
                if (positive.size() < node.getValue()) {
                    positive.add(node.getValue() - 1, new ArrayList<>());
                }
                positive.get(node.getValue() - 1).add(node.getKey().val);
            }
            if (node.getValue() < 0) {
                if (negative.size() < Math.abs(node.getValue())) {
                    negative.add(Math.abs(node.getValue()) - 1, new ArrayList<>());
                }
                negative.get(Math.abs(node.getValue()) - 1).add(node.getKey().val);
            }
            if (node.getKey().left != null) list.offer(new Pair<>(node.getKey().left, node.getValue() - 1));
            if (node.getKey().right != null) list.offer(new Pair<>(node.getKey().right, node.getValue() + 1));

        }
        Collections.reverse(negative);
        results.addAll(negative);
        results.add(zeroL);
        results.addAll(positive);
        return results;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    //Problem Leet code 987
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        helperDFSWithSort(root, 0, 0);
        for (int key : slateSorted.keySet()) {
            Collections.sort(slateSorted.get(key), new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    //if you comment below line you will get answer for leet code 314
                    if (p1.getKey() == p2.getKey()) return p1.getValue() - p2.getValue();
                    else return p1.getKey() - p2.getKey();
                }
            });
            List<Integer> sortedColumn = new ArrayList();
            for (Pair<Integer, Integer> p : slateSorted.get(key)) {
                sortedColumn.add(p.getValue());
            }
            results.add(sortedColumn);
        }
        return results;

    }

    Map<Integer, ArrayList<Pair<Integer, Integer>>> slateSorted = new TreeMap<>();

    private void helperDFSWithSort(TreeNode node, int verticalRank, int level) {

        level = level + 1;
        slateSorted.putIfAbsent(verticalRank, new ArrayList<Pair<Integer, Integer>>());
        slateSorted.get(verticalRank).add(new Pair(level, node.val));

        if (node.left != null) helperDFSWithSort(node.left, verticalRank - 1, level);
        if (node.right != null) helperDFSWithSort(node.right, verticalRank + 1, level);
    }
}
