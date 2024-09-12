package dijkstra.networkDelayTime;

import java.util.*;

public class networkDelayTime1 {
    static class Pair implements Comparable<Pair> {
        int cost;
        int vertex;

        Pair(int cost, int vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }

        public int compareTo(Pair other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new Pair(time[2], time[1]));
        }

        Map<Integer, Integer> minDistance = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, k));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currentVertex = current.vertex;
            int currentCost = current.cost;

            if (!minDistance.containsKey(currentVertex)) {
                minDistance.put(currentVertex, currentCost);
                List<Pair> neighbors = graph.getOrDefault(currentVertex, new ArrayList<>());
                for (Pair neighbor : neighbors) {
                    int nextVertex = neighbor.vertex;
                    int nextCost = currentCost + neighbor.cost;
                    if (!minDistance.containsKey(nextVertex)) {
                        pq.add(new Pair(nextCost, nextVertex));
                    }
                }
            }
        }

        if (minDistance.size() != n) return -1;
        return minDistance.values().stream().max(Integer::compare).get();
    }

    public static void main(String[] args) {
        int[][] times = {{2, 1, 2}, {2, 3, 5}, {2, 4, 1}, {4, 3, 3}};
        int n = 4;
        int k = 2;
        int delay = networkDelayTime(times, n, k);
        System.out.println("Network delay time: " + delay);
    }
}
