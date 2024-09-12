package template.dijkstra;

import java.util.*;

import java.util.*;

public class dijkstra {
    static Map<Integer, List<int[]>> graph = new HashMap<>();
    static Map<Integer, Integer> costs = new HashMap<>();

    public static void main(String[] args) {
        initializeGraph();
        int result = dijkstra(1, 8);
        System.out.println("Minimum cost from 1 to 8 is: " + result);
    }

    private static void initializeGraph() {
        graph.put(1, Arrays.asList(new int[]{2, 2}, new int[]{1, 4}));
        graph.put(2, Arrays.asList(new int[]{1, 3}, new int[]{9, 5}, new int[]{6, 6}));
        graph.put(3, Arrays.asList(new int[]{4, 6}));
        graph.put(4, Arrays.asList(new int[]{3, 3}, new int[]{5, 7}));
        graph.put(5, Arrays.asList(new int[]{1, 8}));
        graph.put(6, Arrays.asList(new int[]{3, 5}));
        graph.put(7, Arrays.asList(new int[]{7, 6}, new int[]{9, 8}));
        graph.put(8, new ArrayList<>());
    }

    public static int dijkstra(int start, int finalDest) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, start}); // Cost, Vertex

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentCost = current[0];
            int currentVertex = current[1];

            if (costs.containsKey(currentVertex)) {
                continue;
            }

            costs.put(currentVertex, currentCost);

            if (currentVertex == finalDest) {
                return currentCost;
            }

            for (int[] neighbor : graph.getOrDefault(currentVertex, new ArrayList<>())) {
                int nextVertex = neighbor[0];
                int additionalCost = neighbor[1];
                if (!costs.containsKey(nextVertex)) {
                    pq.offer(new int[]{currentCost + additionalCost, nextVertex});
                }
            }
        }

        return costs.getOrDefault(finalDest, -1); // Return -1 if no path exists
    }
}
