package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyRoomGraph {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        dfs(0, rooms, visited);
        return visited.size() == rooms.size();

    }

    private void dfs(int i, List<List<Integer>> rooms, Set<Integer> visited) {
        visited.add(i);
        for (Integer neighbour : rooms.get(i)) {
            if (!visited.contains(neighbour))
                dfs(neighbour, rooms, visited);
        }
    }
}
