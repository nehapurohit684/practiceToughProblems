package tree;

import java.util.ArrayList;
import java.util.List;

public class MergeBST {
    public static class Node {
        int val;
        Node left;
        Node right;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
      First convert list to sorted array using in-order and then merge array and create BST again
     * @param root1
     * @param root2
     * @return
     */
    public static Node mergeTwoBSTs(Node root1, Node root2) {
        List<Integer> root1List = new ArrayList<>();
        List<Integer> root2List = new ArrayList<>();
        helperInOrder(root1, root1List);
        helperInOrder(root2, root2List);
        List<Integer> finalList = mergeLists(root1List, root2List);
        return makeBST(finalList, 0, finalList.size() - 1);
    }

    private static Node makeBST(List<Integer> list, int start, int end) {

        if (start > end) return null;
        int mid = (start + end) / 2;
        Node root = new Node(list.get(mid));
        root.left = makeBST(list, start, mid - 1);
        root.right = makeBST(list, mid + 1, end);
        return root;
    }

    private static List<Integer> mergeLists(List<Integer> root1List, List<Integer> root2List) {
        int list1_ptr = 0, list2_ptr = 0;
        List<Integer> result = new ArrayList<>();

        while (list1_ptr < root1List.size() && list2_ptr < root2List.size()) {
            if (root1List.get(list1_ptr) <= root2List.get(list2_ptr)) {
                result.add(root1List.get(list1_ptr++));
            } else {
                result.add(root2List.get(list2_ptr++));
            }
        }
        while (list1_ptr < root1List.size()) {
            result.add(root1List.get(list1_ptr++));
        }
        while (list2_ptr < root2List.size()) {
            result.add(root2List.get(list2_ptr++));
        }

        return result;
    }

    private static void helperInOrder(Node root, List<Integer> rootList) {

        if (root == null) return;
        if (root.left != null) {
            helperInOrder(root.left, rootList);
        }
        rootList.add(root.val);

        if (root.right != null) {
            helperInOrder(root.right, rootList);
        }
    }

}
