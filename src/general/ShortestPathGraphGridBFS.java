package general;

import java.util.*;

public class ShortestPathGraphGridBFS {

    public static void main(String[] args) {
        String[] grid = {"...B",".b#.","@#+."};
        find_shortest_path(grid);
    }

    static int[][] find_shortest_path(String[] grid) {

        List<Grid> graph = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length()];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                Grid g = new Grid(i,j,grid[i].toCharArray()[j]);
                graph.add(g);
                visited[i][j]= false;
            }
        }

        List<int[]> resultMod = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
              if(grid[i].toCharArray()[j]=='@'){
                  Grid start = new Grid(i,j,grid[i].toCharArray()[j]);
                  helperBFS(start,grid,visited,resultMod);
              }
            }
        }
        Collections.reverse(resultMod);
        return resultMod.toArray(new int[0][]);


    }

    private static void helperBFS(Grid start, String[] grid, boolean[][] visited, List<int[]> resultMod) {
        Queue<Grid> queue = new LinkedList<>();
        queue.offer(start);
        List<Character> resultChars = new ArrayList<>();
        while (!queue.isEmpty()){
            Grid p1 = queue.poll();
            visited[p1.row][p1.col]=true;
            for (Grid p : getNeighbours(p1,grid)){
                p.parent=p1;
                if(!visited[p.row][p.col]) {
                    visited[p.row][p.col] =true;
                    Character c = p.value;
                    if ( c== '+') {
                        while (p.parent!=null) {
                            resultMod.add(new int[]{p.row, p.col});
                            p=p.parent;
                        }
                        resultMod.add(new int[]{start.row, start.col});
                        return;
                    }
                    else if ( c== '#') {
                        continue ;
                    }
                    else if(java.lang.Character.isLowerCase(c)){
                        resultChars.add(c);
                        queue.offer(p);
                    }
                    else if (c == '.') {
                        queue.offer(p);
                    }
                    else if(java.lang.Character.isUpperCase(c) && resultChars.contains(java.lang.Character.toLowerCase(c))){
                        queue.offer(p);
                    }

                }

            }
        }
    }

    private static List<Grid> getNeighbours(Grid p1, String[] grid) {
        int i =p1.row;
        int j=p1.col;
        List<Grid> adjList = new ArrayList<>();
        if(i-1>=0)adjList.add(new Grid(i-1,j,grid[i-1].toCharArray()[j]));
        if(i+1<grid.length)adjList.add(new Grid(i+1,j,grid[i+1].toCharArray()[j]));
        if(j-1>=0)adjList.add(new Grid(i,j-1,grid[i].toCharArray()[j-1]));
        if(j+1<grid[0].length())adjList.add(new Grid(i,j+1,grid[i].toCharArray()[j+1]));
        return adjList;
    }


    static class Grid{
        int row, col;
        char value;
        Grid parent;
        List<Grid> adjList;
        public Grid(int row, int col, char val){
            this.row = row;
            this.col = col;
            this.value = val;
            adjList =new ArrayList<>();


        }
    }

}
