public class Node {

    Node left;
    Node right;
    int val;

    Node(int val){
        this.val = val;
    }

    Node(Node left, Node right, int val){
        this.left=left;
        this.right=right;
        this.val = val;
    }
}
