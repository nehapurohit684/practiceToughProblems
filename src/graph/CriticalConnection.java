package graph;

import java.util.*;

public class CriticalConnection {

    /**
     * Bridge edges are edges if removes leave the graph into disconnected components.
     * In a graph back edges forms cycle so they can not be bridge edges
     */
    /**
     * 1192. Critical Connections in a Network
     * Each internal node would add edge to global ans if the edge connecting to its parent is tree edge
     * to find if that edge is tree edge we need to find if there is any back edge from any descendant to any ancestor of the internal node
     * if there is back edge then tree edge from parent to internal node is not a tree edge
     * Every child needs to return the info about back edge - lowest arrival time for any back edge, how high in the tree the back edge can go
     *
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        //build the graph:
        Map<Integer,List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i,new ArrayList<>());
        }

        for (List<Integer> myNe: connections) {
            adjList.get(myNe.get(0)).add(myNe.get(1));
        }
        int[] arrival = new int[n];
        int[] lowestArrival = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(arrival,-1);
        Arrays.fill(lowestArrival,-1);
        Arrays.fill(parent,-1);
        Arrays.fill(visited,false);

        List<List<Integer>> results = new ArrayList<>();

        //DFS
        helperDFS(0,adjList,parent,arrival,lowestArrival,visited,0,results);

        return results;
    }

    int helperDFS(int i, Map<Integer, List<Integer>> adjList, int[] parent,
                  int[] arrival, int[] arr, boolean[] visited,
                  int time, List<List<Integer>> results) {
        arrival[i] = time++;
        visited[i] = true;
        arr[i] = arrival[i];
        boolean flag = false;//not an articulation point
        for (int neighbour : adjList.get(i)) {
            if (!visited[neighbour]) {
                parent[neighbour] = i;
                int myLowestBackEdge = helperDFS(neighbour, adjList, parent, arrival, arr, visited, time, results);
                arr[i] = Math.min(myLowestBackEdge, arr[i]);

                //if we want to find articulation point which if removed breaks the graph in to connected components.
                //if atleast one of my neighbour has a backedge connecting only till my level at max that means if I m gone that neighbour will be gone too
                if (arr[neighbour] <= myLowestBackEdge) flag = true;
            } else if (neighbour != parent[i]) {
                arr[i] = Math.min(arr[i], arr[neighbour]);
            }
        }
        if (arr[i] != arrival[i]) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            list.add(parent[i]);
            results.add(list);
        }
        //if (flag) {// add the node i to results not part of this question so not doing it here, Parent will be articulation point if it has atleast 2 children }
            return arr[i];

        }

    public boolean stronglyConnectedDirected(int n, List<List<Integer>> connections) {
        //build the grapha:
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        Map<Integer,List<Integer>> adjListR = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i,new ArrayList<>());
            adjListR.put(i,new ArrayList<>());
        }

        for (List<Integer> myNe: connections) {
            adjList.get(myNe.get(0)).add(myNe.get(1));
            adjListR.get(myNe.get(1)).add(myNe.get(0));
        }
        boolean[] visited = new boolean[n];
        boolean[] visitedR = new boolean[n];

        int components =0;
        for (int i = 0; i < n; i++) {
            if(visited[i]) {
                components++;
                if(components==2) return false;
                helperDFSDirected(0,visited,adjList);
            }
        }
        helperDFSDirected(0,visitedR,adjListR);
        for (int i = 1; i < n; i++) {
            if(!visitedR[i]) return false;
        }
        return true;
    }

    private void helperDFSDirected(int i, boolean[] visited, Map<Integer, List<Integer>> adjList) {
        visited[i] =true;
        for (int node :adjList.get(i)) {
            if(!visited[node]) helperDFSDirected(node,visited,adjList);
        }
    }


    /**
     * Strongly connected components can also be find with lowest arrival time,
     * if there is backedge from each vertex to all its neighbours or neighbours sub tree
     * in that case you can reach to v from all vertices and all vertices from v
     * @param n
     * @param connections
     * @return
     */
    public boolean stronglyConnected(int n, List<List<Integer>> connections) {

        //build the graph:
        Map<Integer,List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i,new ArrayList<>());
        }

        for (List<Integer> myNe: connections) {
            adjList.get(myNe.get(0)).add(myNe.get(1));
        }
        int[] arrival = new int[n];
        int[] lowestArrival = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(arrival,-1);
        Arrays.fill(lowestArrival,-1);
        Arrays.fill(parent,-1);
        Arrays.fill(visited,false);

        boolean results = true;

        //DFS
        helperDFSConect(0,adjList,parent,arrival,lowestArrival,visited,0,results);

        return results;
    }
    int helperDFSConect(int i, Map<Integer, List<Integer>> adjList, int[] parent,
                  int[] arrival, int[] arr, boolean[] visited,
                  int time, boolean results) {
        arrival[i] = time++;
        visited[i] = true;
        arr[i] = arrival[i];
        for (int neighbour : adjList.get(i)) {
            if (!visited[neighbour]) {
                parent[neighbour] = i;
                int myLowestBackEdge = helperDFSConect(neighbour, adjList, parent, arrival, arr, visited, time, results);
                arr[i] = Math.min(myLowestBackEdge, arr[i]);
            } else //it could back edge , forward or cross edge if there is any such edge we want the lowest arrival time updated for the node
            {
                arr[i] = Math.min(arr[i], arr[neighbour]);
            }
        }
        if (arr[i] ==arrival[i]) {
            //any node has lowest arrival time as it was set at start that means its not strongly connected
            results = false;
        }
        return arr[i];

    }

    /**
     * Kosaraju'a algorithm
     * Do a DFS to compute departure times for each vertex
     * Build the reverse graph
     * in the reverse graph do a dfs on each node in order of decreasing departure times: max departure time is source
     * we want to do second dfs from sink of each SCC because we know if we send some information to all sinks of a DAG then we will cover sending that info to all
     * Number of times second dfs is called tells you that a seperate SCC (strong connected component) is accessed
     * @param n
     * @param connections
     * @return : Number of sources needed to bradcast any message from a graph having few strongly connected componenets
     */
    public int kosaRaju(int n, List<List<Integer>> connections) {

        //build the graph:
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        Map<Integer,List<Integer>> adjListReverse = new HashMap<>();


        for (int i = 0; i < n; i++) {
            adjList.put(i,new ArrayList<>());
            adjListReverse.put(i,new ArrayList<>());
        }

        for (List<Integer> myNe: connections) {
            adjList.get(myNe.get(0)).add(myNe.get(1));
            adjListReverse.get(myNe.get(1)).add(myNe.get(0));
        }
        int[] departure = new int[n];
        boolean[] visited = new boolean[n];
        int[] visitedR = new int[n];

        Arrays.fill(departure,-1);
        Arrays.fill(visited,false);
        Arrays.fill(visitedR,-1);


        List<Integer> listDeparture = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(!visited[i]) dfsKosaRajuAlgo(i,visited,departure,adjList,0,listDeparture);
        }

        //second dfs in decreasing order of departure times with reverse adjList
        Collections.reverse(listDeparture);
        //number of edges that connect one strongly connected to other
        List<List<Integer>> condensedEdges = new ArrayList<>();
        int sscid=0;

        for (int i: listDeparture ) {
            sscid++;
            dfsGeneral(i,visitedR,adjListReverse,sscid,condensedEdges);
        }
        int[] inDegree = new int[sscid];
        for (List<Integer> edge: condensedEdges) {
            inDegree[edge.get(1)]++;
        }
        int numSources=0;
        for (int i =0 ;i<sscid;i++ ) {
            if(inDegree[i]==0) numSources++;
        }
        return numSources;
    }

    private void dfsGeneral(int i, int[] visited, Map<Integer, List<Integer>> adjListReverse,
                            int sscid, List<List<Integer>> condensedEdges) {
        visited[i] = sscid;

        for (int neighbour : adjListReverse.get(i)) {
            if(visited[neighbour]==-1) dfsGeneral(neighbour,visited,adjListReverse,sscid, condensedEdges);
            else if(visited[neighbour]<sscid ) {
                List<Integer> list = new ArrayList<>();
                list.add(visited[neighbour]);
                list.add(sscid);
                condensedEdges.add(list);
            }
        }
    }

    private void dfsKosaRajuAlgo(int i, boolean[] visited, int[] departure, Map<Integer, List<Integer>> adjList, int time, List<Integer> listDeparture) {
        visited[i] = true;

        for (int neighbour : adjList.get(i)) {
            if(!visited[neighbour]) dfsKosaRajuAlgo(neighbour,visited,departure,adjList, time, listDeparture);
        }
        departure[i] = time++;
        //nodes in the listDeparture will be in increasing order of departure time
        listDeparture.add(i);
    }

}
