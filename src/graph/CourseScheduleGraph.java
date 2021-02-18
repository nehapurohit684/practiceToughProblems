package graph;

import java.util.*;

public class CourseScheduleGraph {


    static List<Integer> course_schedule(int n, List<List<Integer>> prerequisites) {
        // Write your code here.
        //build the graph
        Map<Integer,Vertext> graph = new HashMap<>();

        for(int i=0; i< n; i++){
            graph.put(i,new Vertext(i));
        }
        for(List<Integer> listNodes :prerequisites){
            graph.get(listNodes.get(0)).inDegree++;
            graph.get(listNodes.get(1)).neighbours.add(graph.get(listNodes.get(0)));
        }

        List<Integer> results = new ArrayList<>();
        Queue<Vertext> vetices = new LinkedList<>();


        for (Vertext v: graph.values()) {
            if (v.inDegree==0) {
                vetices.offer(v);
            }
        }
        while (!vetices.isEmpty()){
            Vertext vertex = vetices.poll();
            results.add(vertex.val);
            for (Vertext ver:vertex.neighbours){
                ver.inDegree--;
                if(ver.inDegree==0) {
                    vetices.add(ver);
                }
            }
        }
        if (results.size()<n) return Collections.singletonList(-1);
        return results;
    }

    private static class Vertext {
        int inDegree;
        int val;
        List<Vertext> neighbours;

        Vertext(int i){
            this.val=i;
            this.inDegree=0;
            this.neighbours= new ArrayList<>();
        }

    }
}
