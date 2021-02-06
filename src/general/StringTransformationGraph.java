package general;

import java.util.*;

public class StringTransformationGraph {

    public static void main(String[] args) {
        String[] words ={"cat", "hat", "bad", "had"};
        System.out.println(string_transformation(words,"bat","had"));
    }

    static String[] string_transformation(String[] words, String start, String stop) {

        if(start.equals(stop) && words.length==0)return new String[]{"-1"};

        Map<String, Set<String>> graph = new HashMap<>();

        graph.put(start,new HashSet<>());
        graph.put(stop,new HashSet<>());

        for(String word: words){
            graph.put(word,new HashSet<>());
        }
        Map<String,Boolean> visited = new HashMap<>();
        for (String word: graph.keySet()) {
            if(diffChar(word,start) ==1){
                graph.get(start).add(word);
                graph.get(word).add(start);

            }
            if(diffChar(word,stop) ==1){
                graph.get(stop).add(word);
                graph.get(word).add(stop);
            }
            visited.put(word,false);
        }

        for (int i = 0; i < words.length ; i++) {
            for (int j = 0; j < words.length; j++) {
                if(diffChar(words[i],words[j]) ==1){
                    graph.get(words[i]).add(words[j]);
                    graph.get(words[j]).add(words[i]);
                }
            }
        }

        List<String> results = new ArrayList<>();

        helperBFS(start,graph,visited,results,stop);
        return results.toArray(new String[0]);
    }

    private static void helperBFS(String start,Map<String, Set<String>> graph, Map<String, Boolean> visited, List<String> results,String stop) {

        visited.put(start,true);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()){
            String str = queue.poll();
            results.add(str);
            for (String nbr: graph.get(str)) {
                if(nbr.equals(stop)) {
                    results.add(nbr);
                    return;
                }
                else if(!visited.get(nbr)) {
                    visited.put(nbr, true);
                    queue.add(nbr);
                }

            }
        }
    }

    private static int diffChar(String word, String word1) {
        char[] char1 = word.toCharArray();
        char[] char2 = word1.toCharArray();
        int count =0;
        if(char1.length!=char2.length) return -1;
        else{
            for (int i = 0; i < char1.length; i++) {
                if(char1[i]!=char2[i])
                    count++;
            }
        }
        return count;
    }
}
