package tree;


import java.util.Stack;

public class BSTIteratorStack {
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

    Stack<TreeNodeStatus> preOrder = new Stack<>();

    public BSTIteratorStack(TreeNode root) {
        preOrder.push(new TreeNodeStatus(root, "arrival"));
        advance();
    }


    public int next() {
        TreeNodeStatus result = preOrder.peek();
        if (result.node.right != null)
            preOrder.push(new TreeNodeStatus(result.node.right, "arrival"));
        advance();

        return result.node.val;
    }

    private void advance() {

        while (!preOrder.isEmpty()) {
            TreeNodeStatus current = preOrder.peek();
            if (current.status == "arrival") {
                current.status = "interim";
                //Arrival Zone
                if (current.node.left != null)
                    preOrder.push(new TreeNodeStatus(current.node.left, "arrival"));
            } else if (current.status == "interim") {
                current.status = "departure";
                //In Order Zone
                return;
            } else if (current.status == "departure") {
                //Departure or Post Order Zone
                preOrder.pop();
            }
        }
    }

    public boolean hasNext() {
        return preOrder.size() > 0;
    }
}
