import java.beans.PropertyEditorSupport;
import java.util.*;


public class BFS {


    static void printLeftAtEveryLevel(Node root){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            boolean printed;
            printed=false;
            //before level
            while (levelSize-- > 0) {
                Node curr = q.poll();
                    if(!printed){
                        System.out.println(curr.val);
                        printed=true;
                    }
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
            //after level
        }

    }


    static void printZigZagAtEveryLevel(Node root){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            boolean leftRight;
            leftRight=false;
            //before level
            List listNodes = new ArrayList<>();
            while (levelSize-- > 0) {
                Node curr = q.poll();
                listNodes.add(curr);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
            //after level
            if(leftRight) {
                System.out.println(listNodes);
            }else {
                System.out.println(reverseList(listNodes));
            }
            leftRight=!leftRight;
        }

    }

    private static List reverseList(List listNodes) {
     Collections.reverse(listNodes);
        return listNodes;
    }



    public List<List<Integer>> pathSum(Node root, int sum) {
        if(root==null) return Collections.EMPTY_LIST;
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> slate = new ArrayList<>();
        slate.add(root.val);
        helperDFS(root,slate,root.val,results,sum);
        return results;

    }

    public void helperDFS(Node root,List<Integer> temp,int sum, List<List<Integer>> results,int target){
        //leaf node
        if(root.left == null && root.right==null){
            if (sum==target)
                results.add(new ArrayList<>(temp));
            return;
        }
        if(root.left !=null) {
            temp.add(root.left.val);
            helperDFS(root.left,temp ,sum+ root.left.val,results,target);
            temp.remove(temp.size()-1);
        }
        if(root.right!=null) {
            temp.add(root.right.val);
            helperDFS(root.right,temp,sum+ root.right.val,results,target);
            temp.remove(temp.size()-1);
        }
    }
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(9);
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> slate = new ArrayList<>();

    }


    public boolean helperDFSUniVal(Node root,int count){
        //leaf node
        if(root.left == null && root.right==null){
                count++;
            return true;
        }
        boolean left = false,right =false;
        if(root.left !=null) {
             left = helperDFSUniVal(root.left,count);
        }
        if(root.right!=null) {
             right = helperDFSUniVal(root.right,count);
        }
        if(right && left && root.val == root.left.val && root.val==root.right.val){
            count++;
            return true;
        }
        return false;
    }
    public boolean isUnivalTree(Node root) {
        if (root==null) return false;
        if(root.left == null && root.right==null){
            return true;
        }
        boolean left = false,right =false;
        boolean result= true;
        if(root.left !=null) {
            left = isUnivalTree(root.left);
            if (left || root.val!=root.left.val) result= false;
        }
        if(root.right!=null) {
            right = isUnivalTree(root.right);
            if (right || root.val!=root.right.val) result= false;

        }
        return result;
    }

    public Node buildBinaryTree(List<Integer> preorder,int starP,int endP, List<Integer> inorder,int starI,int endI) {
       if(starP>endP) return null;
       if(starP==endP) return new Node(preorder.get(0));

        Node node = new Node(preorder.get(starP));
        int idex = inorder.indexOf(preorder.get(starP));
        int numLeft = idex-starI;
        int numright = endI-idex;

        node.left = buildBinaryTree(preorder,starP+1,starP+numLeft, inorder,starI,idex-1);
        node.right = buildBinaryTree(preorder,starP+numLeft+1,endP, inorder,idex+1,endI);

        return node;
    }

    public Node buildBinaryTreeWithArray(int[] preorder,int starP,int endP, int[] inorder,int starI,int endI,Map<Integer,Integer> preOrderList) {
        if(starP>endP) return null;
        if(starP==endP) return new Node(preorder[starP]);

        Node node = new Node(preorder[starP]);
        int idex = preOrderList.get(preorder[starP]);
        int numLeft = idex-starI;
        int numright = endI-idex;

        node.left = buildBinaryTreeWithArray(preorder,starP+1,starP+numLeft, inorder,starI,idex-1,preOrderList);
        node.right = buildBinaryTreeWithArray(preorder,starP+numLeft+1,endP, inorder,idex+1,endI,preOrderList);

        return node;
    }

    public Node buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return buildBinaryTreeWithArray(preorder,0,preorder.length,inorder,0,inorder.length,map);

    }


    static List<Integer> postorder_traversal(Node root){
        List<Integer> results = new ArrayList<>();

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.add(root);
        while (!nodeStack.empty()){
            Node node = nodeStack.peek();
        if(root.left==null && root.right==null){
            results.add(nodeStack.pop().val);
        }
        if(root.left!=null) nodeStack.push(root.left);
        if(root.right!=null) nodeStack.push(root.right);

            results.add(nodeStack.pop().val);
        }

        return results;
    }

}
