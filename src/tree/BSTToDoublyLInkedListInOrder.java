package tree;

public class BSTToDoublyLInkedListInOrder {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;

    /**
     * Global: Find circular linked list
     * Local: find predecessor or successor of each node
     * IN order traversal makes sure last node we process is your predecessor.
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node sentinel = new Node();
        Node tail = dfs(root, sentinel);
        Node head = sentinel.right;
        head.left = tail;
        tail.right = head;
        return head;
    }

    private Node dfs(Node node, Node pred) {

        //base case
        if (node.left == null && node.right == null) {
            pred.right = node;
            node.left = pred;
            return node;
        }

        //recursive
        if (node.left != null) {
            pred = dfs(node.left, pred);
        }
        pred.right = node;
        node.left = pred;
        pred = node;
        if (node.right != null) {
            pred = dfs(node.right, pred);
        }
        return pred;
    }


}
