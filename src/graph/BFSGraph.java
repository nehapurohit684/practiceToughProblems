package graph;

import java.util.*;

public class BFSGraph {

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

    public static void bfsGraph(Node node) {
        HashMap<Node, Boolean> visited = new HashMap<>();
        Queue<Node> list = new LinkedList<>();
        list.add(node);
        //main difference between Trr and Graph BFS
        while (!list.isEmpty()) {
            Node current = list.poll();
            visited.put(current, true);
            for (Node neighbour : current.neighbors) {
                if (visited.get(neighbour) != null) {
                    list.offer(neighbour);
                }
            }
        }
    }
}
