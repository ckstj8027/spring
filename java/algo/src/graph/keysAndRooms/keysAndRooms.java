package graph.keysAndRooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Solution 1: DFS
class Solution1 {
    static boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        dfs(rooms, 0);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public void dfs(List<List<Integer>> rooms, int v) {
        visited[v] = true;
        for (Integer nextVertex : rooms.get(v)) {
            if (!visited[nextVertex]) {
                dfs(rooms, nextVertex);
            }
        }
    }
}

// Solution 2: BFS
class Solution2 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        bfs(rooms, visited, 0);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public void bfs(List<List<Integer>> rooms, boolean[] visited, int v) {
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.offer(v);
        while (!queue.isEmpty()) {
            int curVertex = queue.poll();
            for (Integer nextVertex : rooms.get(curVertex)) {
                if (!visited[nextVertex]) {
                    queue.offer(nextVertex);
                    visited[nextVertex] = true;
                }
            }
        }
    }
}

public class keysAndRooms{
    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(Arrays.asList(1, 3));
        rooms1.add(Arrays.asList(3, 0, 1));
        rooms1.add(Arrays.asList(2));
        rooms1.add(Arrays.asList(0));

        Solution1 solution1 = new Solution1();
        System.out.println("Solution 1: " + solution1.canVisitAllRooms(rooms1));

        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));

        Solution2 solution2 = new Solution2();
        System.out.println("Solution 2: " + solution2.canVisitAllRooms(rooms2));

        // Additional test case
        List<List<Integer>> rooms3 = new ArrayList<>();
        rooms3.add(Arrays.asList(1));
        rooms3.add(Arrays.asList(2));
        rooms3.add(Arrays.asList(3));
        rooms3.add(Arrays.asList());

        System.out.println("Additional Test Case: ");
        System.out.println("Solution 1: " + solution1.canVisitAllRooms(rooms3));
        System.out.println("Solution 2: " + solution2.canVisitAllRooms(rooms3));
    }
}
