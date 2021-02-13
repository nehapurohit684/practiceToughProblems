package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TransposeGraph {

    static class Node {
        Integer val;
        Vector<Node> neighbours = new Vector<Node>(0);

        Node(Integer _val) {
            val = _val;
            neighbours.clear();
        }
    }

    ;

    static Node build_other_graph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        return helpderDFS(node, visited);

    }

    private static Node helpderDFS(Node node, Map<Node, Node> visited) {
        if (node == null) {
            return null;
        }
        Node nodeRes = new Node(node.val);
        visited.put(node, nodeRes);

        for (Node neighbour : node.neighbours) {
            if (visited.get(neighbour) == null) {
                helpderDFS(neighbour, visited);
            }
            //Add the node to the neighbour as child
            visited.get(neighbour).neighbours.add(nodeRes);
        }

        return nodeRes;
    }


}


