package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFSGraph {

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

    public static void dfsGraph(Node node) {
        Map<Node, Boolean> visited = new HashMap<>();
        visited.put(node, true);
        for (Node neighbour : node.neighbors) {
            if (visited.get(neighbour) != null) {
                dfsGraph(neighbour);
            }
        }
    }
}
