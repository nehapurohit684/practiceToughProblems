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

    public List<Integer> leftSideView(TreeNode root) {
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

    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> results = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);

            }
            double average = sum / size;
            results.add(average);
        }

        return results;
    }

    public int minDepth(TreeNode root) {
        int results = 0;
        if (root == null) return results;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            results++;
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                if (node.left == null && node.right == null) return results;
                if (node.left != null) list.offer(node.left);
                if (node.right != null) list.offer(node.right);
            }
        }
        return results;

    }

    public int maxDepth(TreeNode root) {
        int results = 0;
        if (root == null) return results;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            results++;
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                if (node.left != null) list.offer(node.left);
                if (node.right != null) list.offer(node.right);
            }
        }
        return results;
    }

    public int maxLevelSum(TreeNode root) {
        int resLevel = 0;
        if (root == null) return resLevel;
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            level++;
            int levelS = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                levelS += node.val;
                if (node.left != null) list.offer(node.left);
                if (node.right != null) list.offer(node.right);
            }
            if (levelS > maxSum) {
                maxSum = levelS;
                resLevel = level;
            }
        }
        return resLevel;
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return false;
        boolean[] result = new boolean[1];
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        int uniValue = root.val;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                if (node.val != uniValue) return false;
                if (node.left != null) {
                    list.offer(node.left);
                }
                if (node.right != null) {
                    list.offer(node.right);
                }
            }
        }
        return true;
    }

    /**
     * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        int parentX = 0, parentY = 0;
        int levelX = 0, levelY = 0;
        int level = 1;
        while (!list.isEmpty()) {
            int size = list.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode current = list.poll();
                if (current.left != null) {
                    if (current.left.val == x) {
                        parentX = current.val;
                        levelX = level;
                    }
                    if (current.left.val == y) {
                        parentY = current.val;
                        levelY = level;
                    }
                    list.offer(current.left);
                }
                if (current.right != null) {
                    if (current.right.val == x) {
                        parentX = current.val;
                        levelX = level;
                    }
                    if (current.right.val == y) {
                        parentY = current.val;
                        levelY = level;
                    }
                    list.offer(current.right);
                }
                if (parentY != 0 && parentY != 0 && levelX != 0 && levelY != 0 && levelX == levelY && parentX != parentY)
                    return true;
            }
        }
        return false;
    }

    /**
     * Populate each next pointer to point to its next right node.
     * If there is no next right node, the next pointer should be set to NULL
     *
     * @param root
     * @return
     */
    public Node1 connect(Node1 root) {
        if (root == null) return null;
        Queue<Node1> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            Node1 prev = null;
            for (int i = 0; i < size; i++) {
                Node1 current = list.poll();
                if (current.left != null) {
                    list.offer(current.left);
                }
                if (current.right != null) {
                    list.offer(current.right);
                }
                if (prev != null) {
                    prev.next = current;
                }
                prev = current;
            }

        }
        return root;
    }

    /**
     * Populate each next pointer to point to its next right node.
     * If there is no next right node, the next pointer should be set to next level
     *
     * @param root
     * @return
     */
    public Node1 connectAll(Node1 root) {
        if (root == null) return null;
        Queue<Node1> list = new LinkedList<>();
        list.add(root);
        Node1 prev = null;
        while (!list.isEmpty()) {
            Node1 current = list.poll();
            if (current.left != null) {
                list.offer(current.left);
            }
            if (current.right != null) {
                list.offer(current.right);
            }
            if (prev != null) {
                prev.next = current;
            }
            prev = current;
        }
        return root;
    }

    /**
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d.
     * The root node is at depth 1
     * Hint: Do pointer manuplation at when given depth= level
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        if (d == 1) {
            TreeNode newNode = new TreeNode(v);
            newNode.left = root;
            root = newNode;
        }
        int level = 1;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = list.poll();
                if (current.left != null) list.add(current.left);
                if (current.right != null) list.add(current.right);
                if (level == d - 1) {
                    TreeNode newLeft = new TreeNode(v);
                    newLeft.left = current.left;
                    current.left = newLeft;
                    TreeNode newRight = new TreeNode(v);
                    newRight.right = current.right;
                    current.right = newRight;
                    break;
                }
            }
            level++;
        }
        return root;
    }

    public int maxWidthOfBinaryTree(TreeNode root) {
        Queue<NodeObject> list = new LinkedList<>();
        list.add(new NodeObject(root, 1));
        int maxWidth = Integer.MIN_VALUE;
        while (!list.isEmpty()) {
            int size = list.size();
            int first = 0, last = 0;
            for (int i = 0; i < size; i++) {
                NodeObject current = list.poll();
                int id = current.id;
                if (current.node.left != null) list.offer(new NodeObject(current.node.left, 2 * id));
                if (current.node.right != null) list.offer(new NodeObject(current.node.right, 2 * id + 1));
                last = id;
                if (first == 0) first = id;
                maxWidth = Math.max(maxWidth, (last - first + 1));
            }
        }
        return maxWidth;
    }

    public boolean isCompleteTree(TreeNode root) {
        Queue<NodeObject> list = new LinkedList<>();
        list.add(new NodeObject(root, 1));
        while (!list.isEmpty()) {
            int size = list.size();
            int expected = 1;
            for (int i = 0; i < size; i++) {
                NodeObject current = list.poll();
                int id = current.id;
                if (expected == id) {
                    expected++;
                } else return false;
                if (current.node.left != null) list.offer(new NodeObject(current.node.left, 2 * id));
                if (current.node.right != null) list.offer(new NodeObject(current.node.right, 2 * id + 1));
            }
        }
        return true;
    }

    /**
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null && q != null || p != null && q == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (first.val != second.val) return false;
            if (first.left != null && second.left != null) {
                queue.add(first.left);
                queue.add(second.left);
            } else if (second.left != null || first.left != null) {
                return false;
            }
            if (first.right != null && second.right != null) {
                queue.add(first.right);
                queue.add(second.right);
            } else if (second.right != null || first.right != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        Queue<NodePair> list = new LinkedList<>();
        list.add(new NodePair(root, root));
        while (!list.isEmpty()) {
            NodePair np = list.poll();
            if (np.node1.left != null && np.node2.right != null) {
                list.add(new NodePair(np.node1.left, np.node2.right));
            } else if (np.node1.left != null || np.node2.right != null) {
                return false;
            }
            if (np.node1.right != null && np.node2.left != null) {
                list.add(new NodePair(np.node1.right, np.node2.left));
            } else if (np.node1.right != null || np.node2.left != null) {
                return false;
            }
            if (np.node1.val != np.node2.val) return false;
        }
        return true;
    }

    class NodePair {
        TreeNode node1;
        TreeNode node2;

        NodePair(TreeNode _node1, TreeNode _node2) {
            node1 = _node1;
            node2 = _node2;
        }
    }

    class NodeObject {
        TreeNode node;
        int id;

        NodeObject(TreeNode _node, int _id) {
            this.node = _node;
            this.id = _id;
        }
    }

    class Node1 {
        public int val;
        public Node1 left;
        public Node1 right;
        public Node1 next;

        public Node1() {
        }

        public Node1(int _val) {
            val = _val;
        }

        public Node1(int _val, Node1 _left, Node1 _right, Node1 _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

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

    public class Node {
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
