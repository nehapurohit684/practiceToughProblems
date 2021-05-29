package general;

import javafx.util.Pair;

import java.util.*;

public class BFSGraph {


    public static void main(String[] args) {

        int [][] array = {{1,0}};
        int n = 2;
        countComponents(n,array);
        System.out.println(validTree(n,array));

    }

    public static int countComponents(int n, int[][] edges) {
        boolean [] visited = new boolean[n];
       Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i <n ; i++) {
            adjList.put(i,new ArrayList<>());
        }
        for (int[] edge :edges ) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Map<Integer,Boolean> visited1 = new HashMap<>();
        visited1.containsKey(1);

        int connected =0;
        for (int i = 0; i <n ; i++) {
            if(!visited[i]) {
                bfs(i, visited, adjList);
                connected++;
            }
        }
        return connected;
    }

    private static void bfs(int node, boolean[] visited, Map<Integer, List<Integer>> edges) {
        Queue<Integer> nodes = new LinkedList<>();
        visited[node] =true;
        nodes.add(node);
        while (!nodes.isEmpty()) {
            int temp = nodes.poll();
            for (Integer i : edges.get(temp)) {
                if (!visited[i]) {
                    visited[i] = true;
                    nodes.add(i);
                }
            }
        }
    }

    public static boolean validTree(int n, int[][] edges) {
        boolean [] visited = new boolean[n];
        int parent[] = new int[n];
        Arrays.fill(parent,-1);

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i <n ; i++) {
            adjList.put(i,new ArrayList<>());
        }
        for (int[] edge :edges ) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int connected =0;
        boolean hasCycle=false;
        for (int i = 0; i <n ; i++) {
            if (!visited[i]) {
                hasCycle = helperBFS(i, visited, parent, adjList);
                connected++;
                if (connected > 1 || hasCycle) return false;
            }
        }
        return true;
    }

    private static boolean helperBFS(int node, boolean[] visited, int[] parent, Map<Integer, List<Integer>> adjList) {

        Queue<Integer> nodes = new LinkedList<>();
        visited[node] =true;
        nodes.add(node);
        while (!nodes.isEmpty()) {
            int temp = nodes.poll();
                for (Integer j : adjList.get(temp)) {
                    if (!visited[j]) {
                        if(adjList.get(parent[j]).contains(j)) return
                        visited[j] = true;
                        parent[j] = temp;
                        nodes.add(j);
                    } else {
                        //if neighbour of node temp is visited and neighbour is not its parent then its a cross edge
                        if (j != parent[temp]) return true;
                    }
            }

        }
        return false;
    }

    private static boolean helperDFS(int node, boolean[] visited, int[] parent, Map<Integer, List<Integer>> adjList) {
        visited[node] =true;
        for (Integer j : adjList.get(node)) {
          if (!visited[j]) {
                    visited[j] = true;
                    parent[j] = node;
                    if(helperDFS(j,visited,parent,adjList)) return true;
                } else {
                    //if neighbour of node temp is visited and neighbour is not its parent then its a cross edge
                    if (j != parent[node]) return true;
                }
            }
        return false;
    }

    private static boolean helperBipartite(int node, boolean[] visited, int[] level,int[][] adjList) {

        Queue<Integer> nodes = new LinkedList<>();
        visited[node] =true;
        nodes.add(node);
        level[node]=0;
        while (!nodes.isEmpty()) {
                int temp = nodes.poll();
                for (int j : adjList[temp]) {
                    if (!visited[j]) {
                        visited[j] = true;
                        level[j]=level[temp]+1;
                        nodes.add(j);
                    }else{
                        if (level[j]==level[temp]) return false;
                    }
                }
            }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        boolean [] visited = new boolean[graph.length];
        int[] level = new int[graph.length];

        for (int i = 0; i <graph.length ; i++) {
            if (!visited[i]) {
                if (!helperBipartite(i, visited, level, graph)) return false;
            }
        }
        return true;

    }
    public int numIslands(char[][] grid) {
        int count =0;
        for (int row = 0; row < grid.length ; row++) {
            for (int col = 0; col < grid[0].length ; col++) {
                if(grid[row][col] !='0'){
                    dfs(row,col,grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y, char[][] grid) {
        grid[x][y]='0';
        if(x<0 || x>grid.length || y<0 || y>grid[0].length) return;
        if(x-1>=0) dfs(x-1,y,grid);
        if(y-1>=0) dfs(x,y-1,grid);
        if(x+1<grid.length) dfs(x+1,y,grid);
        if(y+1<grid[0].length) dfs(x,y+1,grid);

    }

    private List<Pair<Integer, Integer>> getNeigbours(int x, int y,char[][] grid) {
        List<Pair<Integer, Integer>> results = new ArrayList<>();

        if(x-1>=0) results.add(new Pair(x-1,y));
        if(y-1>=0) results.add(new Pair(x,y-1));
        if(x+1<grid.length) results.add(new Pair(x+1,y));
        if(y+1<grid[0].length) results.add(new Pair(x,y+1));

        return results;
    }


}
