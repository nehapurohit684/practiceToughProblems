package general;

import javafx.util.Pair;

import java.util.*;


public class ShortestPathGraph {

    static int[][] find_shortest_path(String[] grid) {

        Map<String, List<String>> graph = new HashMap<>();
        List<List<Integer>> results = new ArrayList<>();
        List<Character> resultChars = new ArrayList<>();

        for (int row = 0; row < grid.length ; row++) {
            for (int col = 0; col < grid[0].toCharArray().length ; col++) {
                if(grid[row].toCharArray()[col]=='@')
                helpeDFS(row,col,grid,results,resultChars);
            }
            }
        int[][] finalData = new int[results.size()][2];
        for (int i = 0; i < results.size(); i++) {
            List<Integer> row = results.get(i);
            for (int j = 0; j < row.size(); j++)
                finalData[i][j] = row.get(j);
        }

        return finalData;
        }

    private static void helpeDFS(int row, int col, String[] grid, List<List<Integer>> results, List<Character> resultChars) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(row);
        list.add(col);
        results.add(list);
        resultChars.add(grid[row].toCharArray()[col]);

        if(row<0 || row>grid.length || col<0 || col>grid[0].length()) return;

        if(row-1>=0) {
            if(grid[row-1].toCharArray()[col]=='#') return;
                if(grid[row-1].toCharArray()[col]=='.') {
                resultChars.add(grid[row-1].toCharArray()[col]);
                helpeDFS(row-1,col,grid,results, resultChars);
            }
            if(java.lang.Character.isUpperCase(grid[row-1].toCharArray()[col])) {
                if(resultChars.contains(java.lang.Character.toLowerCase(grid[row-1].toCharArray()[col]))){
                    resultChars.add(grid[row-1].toCharArray()[col]);
                    helpeDFS(row-1,col,grid,results, resultChars);
                }else return;

            }
            if(grid[row-1].toCharArray()[col]=='+') {
                List<Integer> list1 = new ArrayList<Integer>();
                list.add(row);
                list.add(col);
                results.add(list1);
                return;
            }
        }
        if(row+1<grid.length) {
            if(grid[row+1].toCharArray()[col]=='#') return;
            if(grid[row+1].toCharArray()[col]=='.') {
                resultChars.add(grid[row+1].toCharArray()[col]);
                helpeDFS(row+1,col,grid,results, resultChars);
            }
            if(java.lang.Character.isUpperCase(grid[row+1].toCharArray()[col])) {
                if(resultChars.contains(java.lang.Character.toLowerCase(grid[row+1].toCharArray()[col]))){
                    resultChars.add(grid[row+1].toCharArray()[col]);
                    helpeDFS(row+1,col,grid,results, resultChars);
                }else return;
            }
            if(grid[row+1].toCharArray()[col]=='+'){
                List<Integer> list2 = new ArrayList<Integer>();
                list.add(row);
                list.add(col);
                results.add(list2);                return;
            }
        }
        if(col-1<=0) {
            if(grid[row].toCharArray()[col-1]=='#') return;
            if(grid[row].toCharArray()[col-1]=='.') {
                resultChars.add(grid[row].toCharArray()[col-1]);
                helpeDFS(row,col-1,grid,results, resultChars);
            }
            if(java.lang.Character.isUpperCase(grid[row].toCharArray()[col-1])) {
                if(resultChars.contains(java.lang.Character.toLowerCase(grid[row].toCharArray()[col-1]))){
                    resultChars.add(grid[row].toCharArray()[col-1]);
                    helpeDFS(row,col-1,grid,results, resultChars);
                }else return;
            }
            if(grid[row].toCharArray()[col]=='+'){
                List<Integer> list3 = new ArrayList<Integer>();
                list.add(row);
                list.add(col);
                results.add(list3);
                return;
            }
        }
        if(col+1<grid[0].toCharArray().length) {
            if(grid[row].toCharArray()[col+1]=='#') return;
            if(grid[row].toCharArray()[col+1]=='.') {
                resultChars.add(grid[row].toCharArray()[col+1]);
                helpeDFS(row,col-1,grid,results, resultChars);
            }
            if(java.lang.Character.isUpperCase(grid[row].toCharArray()[col+1])) {
                if(resultChars.contains(java.lang.Character.toLowerCase(grid[row].toCharArray()[col+1]))){
                    resultChars.add(grid[row].toCharArray()[col+1]);
                    helpeDFS(row,col+1,grid,results, resultChars);
                }else return;
            }
            if(grid[row].toCharArray()[col]=='+'){
                List<Integer> list4 = new ArrayList<Integer>();
                list.add(row);
                list.add(col);
                results.add(list4);
                return;
            }
        }
    }

}

    
    

