package tree;


import java.lang.reflect.Array;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * we need to find mid point to create root node each time
     * We use Hare Tortoise approach to find mid
     * to Disconnect connection from mid to before Listnode we use prev node in Hare Tortoise and at last prev.next ==null t break connection
     *
     * @param head
     * @return
     */
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
        ListNode pred = null;

        while (doublePtr != null && doublePtr.next != null) {
            pred = singlePtr;
            singlePtr = head.next;
            doublePtr = head.next.next;
        }
        //to break the listnode
        if (pred != null) pred.next = null;

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

    /**
     * Thats the method find index of in order elements
     *
     * @param inorder
     * @return
     */
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


    /**
     * You convert pre order to Sorted array then use BST code
     * we can not use code to create BST here from sorted array because we are anot asked to create balanced tree
     * Also pre Order first element defines root and not the mid point of inorder
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] preOrderF = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(preorder);
        Map<Integer, Integer> inOrderhash = makeHashMap(preorder);
        return helperBuildTreePreOrderInOrderArray(preOrderF, 0, preOrderF.length - 1, inOrderhash, 0, inOrderhash.size() - 1);
    }

    private TreeNode helperBuildTreePreOrderInOrderArray(int[] preorder, int pStart, int pEnd, Map<Integer, Integer> inOrderhash, int iStart, int iEnd) {
        if (pStart > pEnd) return null;
        if (pStart == pEnd) return new TreeNode(preorder[pStart]);

        TreeNode root = new TreeNode(preorder[pStart]);
        int nodeIndex = inOrderhash.get(preorder[pStart]);
        int nodeLeft = nodeIndex - iStart;
        root.left = helperBuildTreePreOrderInOrderArray(preorder, pStart + 1, pStart + nodeLeft, inOrderhash, iStart, nodeIndex - 1);
        root.right = helperBuildTreePreOrderInOrderArray(preorder, pStart + nodeLeft + 1, pEnd, inOrderhash, nodeIndex + 1, iEnd);
        return root;
    }

    Map<Integer, Integer> makeHashMap(int[] inOrder) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            result.put(inOrder[i], i);
        }
        return result;
    }


    public TreeNode buildTreeFromPost(int[] inorder, int[] postorder) {

        Map<Integer, Integer> map = makeHashMap(inorder);
        return helperBuildTreePostOrderInOrder(postorder, 0, postorder.length - 1, map, 0, inorder.length - 1);

    }

    /**
     * InOrderL Root InorderL
     * PostOrderL PostOrderR Root
     *
     * @param postorder
     * @param startP
     * @param endP
     * @param map
     * @param startI
     * @param endI
     * @return
     */
    private TreeNode helperBuildTreePostOrderInOrder(int[] postorder, int startP, int endP, Map<Integer, Integer> map, int startI, int endI) {

        if (startP > endP) return null;
        if (startP == endP) return new TreeNode(postorder[startP]);
        TreeNode root = new TreeNode(postorder[endP]);
        int nodeIndex = map.get(postorder[endP]);
        int nodeLeft = nodeIndex - startI;
        root.left = helperBuildTreePostOrderInOrder(postorder, startP, startP + nodeLeft - 1, map, startI, nodeIndex - 1);
        root.right = helperBuildTreePostOrderInOrder(postorder, startP + nodeLeft, endP - 1, map, nodeIndex + 1, endI);

        return root;

    }

}
