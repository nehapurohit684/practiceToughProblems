package design;

import java.util.*;

public class LogSystem {

    Map<String, Integer> gran = Map.of("Year", 4, "Month", 7, "Day", 10, "Hour", 13, "Minute", 16, "Second", 19);
    String lower = "2000:01:01:00:00:00";
    String upper = "2017:12:31:23:59:59";

    TreeMap<String, Set<Integer>> map;

    public LogSystem() {
        map = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
        map.putIfAbsent(timestamp, new HashSet<>());
        map.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> list = new ArrayList<>();
        var i = gran.get(granularity);
        String prev = start.substring(0, i).concat(lower.substring(i));
        String after = end.substring(0, i).concat(upper.substring(i));
        prev = map.ceilingKey(prev);
        while (prev != null && prev.compareTo(after) <= 0) {
            list.addAll(new ArrayList(map.get(prev)));
            prev = map.higherKey(prev);
        }
        return list;
    }
}
