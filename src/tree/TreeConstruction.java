package tree;


import java.util.HashMap;

public class TreeConstruction {

    public TreeNode sortedArrayToBST(int[] nums) {
        return helperAVLTree(nums, 0, nums.length - 1);
    }

    private TreeNode helperAVLTree(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(nums[start]);

        //Recursive Case
        int mid = (start + (end - start)) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helperAVLTree(nums, start, mid - 1);
        node.right = helperAVLTree(nums, mid + 1, end);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        return helperAVLTreeListNode(head);
    }

    private TreeNode helperAVLTreeListNode(ListNode head) {
        if (head == null) return null;
        ListNode mid = findMidElement(head);
        TreeNode node = new TreeNode(mid.val);
        if (head == mid) return node;
        node.left = helperAVLTreeListNode(head);
        node.right = helperAVLTreeListNode(mid);
        return node;
    }

    private ListNode findMidElement(ListNode head) {
        ListNode singlePtr = head;
        ListNode doublePtr = head;
        ListNode current = null;

        while (doublePtr != null && doublePtr.next != null) {
            current = singlePtr;
            singlePtr = head.next;
            doublePtr = head.next.next;
        }
        if (current != null) current.next = null;

        return singlePtr;

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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

    /**
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
     * construct and return the binary tree.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inOrderhash = makeHashTable(inorder);
        return helperBuildTreePreOrderInOrder(preorder, 0, preorder.length - 1, inOrderhash, 0, inorder.length - 1);
    }

    private HashMap<Integer, Integer> makeHashTable(int[] inorder) {
        HashMap<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            results.put(inorder[i], i);
        }
        return results;
    }

    private TreeNode helperBuildTreePreOrderInOrder(int[] preorder, int startP, int endP, HashMap<Integer, Integer> inorder, int startI, int endI) {
        //BaseCase
        if (startP > endP) return null;
        if (startP == endP || startI == endI) return new TreeNode(preorder[startP]);

        TreeNode node = new TreeNode(preorder[startP]);
        int nodeIndex = inorder.get(preorder[startP]);
        int numleft = nodeIndex - startI;
        node.left = helperBuildTreePreOrderInOrder(preorder, startP + 1, startP + numleft, inorder, startI, nodeIndex - 1);
        node.right = helperBuildTreePreOrderInOrder(preorder, startP + numleft + 1, endP, inorder, nodeIndex + 1, endI);

        return node;

    }

}
