package tree;

import java.util.*;

public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> resultsList = new ArrayList<>();
            ;
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                resultsList.add(node.val);
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
            }
            results.add(resultsList);
        }
        return results;
    }

    public List<Integer> levelOrderLeftMost(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> resultsList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                resultsList.add(node.val);
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
            }
            results.add(resultsList.get(0));
        }
        return results;
    }


    public List<List<Integer>> levelOrderZigZag(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            boolean zigzag = false;
            List<Integer> resultsList = new ArrayList<>();
            ;
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                resultsList.add(node.val);
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
            }
            if (zigzag) {
                Collections.reverse(resultsList);
                results.add(resultsList);
            } else {
                results.add(resultsList);
            }
            zigzag = !zigzag;
        }
        return results;
    }

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while ((!queue.isEmpty())) {
            int size = queue.size();
            int temp = 0;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                temp = current.val;
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            results.add(temp);
        }
        return results;
    }

    public List<List<Integer>> levelOrderNary(Node root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<Node> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> resultsList = new ArrayList<>();
            ;
            for (int i = 0; i < size; i++) {
                Node node = list.poll();
                resultsList.add(node.val);
                for (Node neighbour : node.children) {
                    if (neighbour != null) list.add(neighbour);
                }
            }
            results.add(resultsList);
        }
        return results;
    }


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


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


}
