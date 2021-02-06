package general;

import java.util.*;

public class DictionaryGraph {

    public static void main(String[] args) {
        String[] words ={"z","z"};
        System.out.println(find_order(words));
    }

    static String find_order(String[] words) {

        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new ArrayList<>());
            }
        }
        for (int i = 0; i < words.length-1 ; i++) {
            if (words[i].length() > words[i+1].length() && words[i].startsWith(words[i+1])) {
                return "";
            }
            int c = findDiffChar(words[i],words[i+1]);
            Character key = words[i].length()>c ? words[i].toCharArray()[c]: null;
            Character value = words[i+1].length()>c ? words[i+1].toCharArray()[c]: null;
            if(key!=null && value!=null) graph.get(key).add(value);
        }

        Map<Character,Boolean> visited = new HashMap<>();
        Map<Character,Integer> departure = new HashMap<>();
        for (Character c: graph.keySet()) {
            visited.put(c,false);
            departure.put(c,-1);
        }

        List<Character> results = new ArrayList<>();
        int time=0;
        for (Character c :graph.keySet()) {
            if (!visited.get(c)) {
                if(helperDFS(c,graph,visited,time,departure,results))
                    return "";
            }
        }
        Collections.reverse(results);
        StringBuilder sb = new StringBuilder();
        for (Character c1:results) {
            sb.append(c1);
        }
        if (sb.length() < graph.size()) {
            return "";
        }
        return sb.toString();

    }

    private static int findDiffChar(String word, String word1) {
        int len = word.length()>word1.length() ? word1.length(): word.length();
        for (int i = 0; i < len; i++) {
            if(word.charAt(i)!=word1.charAt(i))
                return i;
        }
        return len;
    }

    private static boolean helperDFS(Character node,Map<Character, List<Character>> graph, Map<Character,Boolean> visited,int time, Map<Character,Integer> departure, List<Character> results) {
        visited.put(node,true);

        for (Character neighbour: graph.get(node)) {
            if(!visited.get(neighbour)) {
                if (helperDFS(neighbour, graph, visited, time, departure, results)) return true;
            }else{
                    if(departure.get(neighbour)==-1) return true;
                }
        }
        departure.put(node,time++);
        results.add(node);
        return false;
    }
}
