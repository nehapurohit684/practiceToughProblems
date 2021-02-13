package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> resultMap = new HashMap<>();
        helper(node, resultMap);
        return resultMap.get(node);
    }

    private void helper(Node node, Map<Node, Node> visited) {
        if (node == null) return;
        Node cloneNode = new Node(node.val);
        if (node.neighbors == null) return;

        visited.put(node, cloneNode);

        for (Node neighbour : node.neighbors) {
            if (!visited.containsKey(neighbour)) {
                helper(neighbour, visited);
            }
            //add the neighbour to cloneNode as a child
            cloneNode.neighbors.add(visited.get(neighbour));
        }
    }
}
