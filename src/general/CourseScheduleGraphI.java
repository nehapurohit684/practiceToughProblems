package general;

import java.util.*;

public class CourseScheduleGraphI {

    static List<Integer> course_schedule(int n, List<List<Integer>> prerequisites) {

        //build the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i,new ArrayList<>());
        }

        for(List<Integer> relation: prerequisites){
            graph.get(relation.get(1)).add(relation.get(0));
        }
        List<Integer> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] departure = new int[n];
        for (int i = 0; i < n; i++) {
            departure[i] =-1;
        }
        int time=0;
        for (int i = 0; i <n ; i++) {
            if (!visited[i]) {
                if(helperDFS(i,graph,visited,time,departure,results))
                    return Collections.singletonList(-1);
            }
        }
        Collections.reverse(results);
        return results;
    }

    private static boolean helperDFS(int node,Map<Integer, List<Integer>> graph, boolean[] visited,int time, int[] departure, List<Integer> results) {
    visited[node]=true;

        for (int neighbour: graph.get(node)) {
            if(!visited[neighbour])
                if(helperDFS(neighbour,graph,visited,time,departure,results)) return true;
            else{
                if(departure[neighbour]==0) return true;
                }
        }
        departure[node] =  time++;
        results.add(node);
        return false;
    }
}
