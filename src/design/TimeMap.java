package design;

import java.util.Map;
import java.util.TreeMap;

/**
 * 981. Time Based Key-Value Store
 */
public class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new TreeMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).putIfAbsent(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (map.get(key) == null) return null;
        Integer fkey = map.get(key).floorKey(timestamp);
        if (fkey != null) return map.get(key).get(fkey);
        return null;
    }
}
