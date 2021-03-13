package tree;

import javafx.util.Pair;

import java.util.*;

public class PreOrderTraversal {
    public static class TreeNode {
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

    public static class TreeNodeStatus {
        String status;
        TreeNode node;

        TreeNodeStatus() {
        }

        TreeNodeStatus(TreeNode _node, String _status) {
            status = _status;
            node = _node;
        }

    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        helperDFS(root, results);
        return results;
    }

    private static void helperDFS(TreeNode root, List<Integer> results) {
        results.add(root.val);
        if (root.left != null)
            helperDFS(root.left, results);
        if (root.right != null)
            helperDFS(root.right, results);

    }


    public static List<Integer> preorderTraversalStack(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        Stack<TreeNodeStatus> stack = new Stack<>();
        stack.push(new TreeNodeStatus(root, "arrival"));
        while (!stack.isEmpty()) {
            TreeNodeStatus current = stack.peek();
            if (current.status == "arrival") {
                current.status = "interim";
                //Arrival Zone
                results.add(current.node.val);
                if (current.node.left != null)
                    stack.push(new TreeNodeStatus(current.node.left, "arrival"));
            } else if (current.status == "interim") {
                current.status = "departure";
                //In Order Zone
                if (current.node.right != null)
                    stack.push(new TreeNodeStatus(current.node.right, "arrival"));
            } else if (current.status == "departure") {
                //Departure or Post Order Zone
                stack.pop();
            }
        }
        return results;
    }

    public static List<Integer> preorderTraversalLinkedList(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        LinkedList<TreeNodeStatus> linkedList = new LinkedList<>();
        linkedList.push(new TreeNodeStatus(root, "arrival"));
        while (!linkedList.isEmpty()) {
            TreeNodeStatus current = linkedList.peekFirst();
            if (current.status == "arrival") {
                current.status = "interim";
                //Arrival Zone
                results.add(current.node.val);
                if (current.node.left != null)
                    linkedList.push(new TreeNodeStatus(current.node.left, "arrival"));
            } else if (current.status == "interim") {
                current.status = "departure";
                //In Order Zone
                if (current.node.right != null)
                    linkedList.push(new TreeNodeStatus(current.node.right, "arrival"));
            } else if (current.status == "departure") {
                //Departure or Post Order Zone
                linkedList.pollFirst();
            }
        }
        return results;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node1.left = node2;
        node2.left = node3;
        preorderTraversalStack(node1);
    }
}
