package general;

import java.util.*;

public class TransposeGraph {

    /*
     * Complete the function below.
     */

    /*
        For your reference:
    */
        static class Node
    	{
    		Integer val;
    		Vector<Node> neighbours = new Vector<Node>(0);
    		Node(Integer _val)
    		{
    			val = _val;
    			neighbours.clear();
    		}
    	};

    static Node build_other_graph(Node node)
    {
        Map<Node,Node> visited = new HashMap<>();
        return helpderDFS(node,visited);

    }

    private static Node helpderDFS(Node node,  Map<Node,Node> visited) {
        if (node == null) {
            return null;
        }
        Node nodeRes = new Node(node.val);
        visited.put(node,nodeRes);

        for (Node neighbour: node.neighbours) {
            if(visited.get(neighbour)==null) {
                helpderDFS(neighbour,visited);
            }
            visited.get(neighbour).neighbours.add(nodeRes);
        }

        return nodeRes;
    }



}


