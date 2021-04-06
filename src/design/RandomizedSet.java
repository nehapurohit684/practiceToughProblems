package design;

import java.util.*;

public class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.putIfAbsent(val, list.size());
        list.add(list.size(), val);
        return true;

    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int idx = map.get(val);
        int toBeRemoved = list.get(idx);
        list.set(idx, list.get(list.size() - 1));
        map.put(list.get(list.size() - 1), idx);
        list.add(list.get(list.size() - 1), toBeRemoved);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int ran = random.nextInt(list.size());
        return list.get(ran);
    }
}
